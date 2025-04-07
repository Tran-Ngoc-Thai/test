package test.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import test.test.entity.Attendance;
import test.test.entity.Users;
import test.test.entity.WorkingHour;
import test.test.repository.AttendanceRepo;
import test.test.repository.UsersRepo;
import test.test.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceRepo attendanceRepo;
    private final UsersRepo usersRepo;
    private final AttendanceService service;

    @GetMapping("/get")
    public  String getAll(Model model) {
        Attendance att = new Attendance();
        model.addAttribute("att", att);
        List<Users> userList = usersRepo.findAll();
        model.addAttribute("userList", userList);
        List<Attendance> attList = attendanceRepo.findAll();
        model.addAttribute("attList", attList);
        return "gdAdmins/attendance";
    }

     @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Attendance attendance = service.getById(id);
            if (attendance == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy !");
            } else {
                model.addAttribute("att", attendance);
                List<Users> userList = usersRepo.findAll();
                model.addAttribute("userList", userList);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }

        List<Attendance> attList = attendanceRepo.findAll();
        model.addAttribute("attList", attList);
        return "gdAdmins/attendance";
    }


    @PostMapping("/create")
    public String create(Attendance att,
                        RedirectAttributes redirectAttributes) {
       try {
        if (att.getStatus()== null || att.getWorkDate() == null ) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
        } else {
            service.create(att);
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        }
       } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
       }

       

        return "redirect:/attendance/get";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Attendance att , RedirectAttributes redirectAttributes) {
        try {
            Attendance attendance = service.getById(att.getAttendanceID());
            if (attendance == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng!");
                return "redirect:/attendance/get";
            }

            // Kiểm tra ID chức vụ
            if (att.getUser() != null && att.getUser().getUserID() != null) {
                Users users = usersRepo.findById(att.getUser().getUserID())
                    .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
                att.setUser(users);
            }

            
            service.update(att.getAttendanceID(), att);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/attendance/get";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật!");
            return "redirect:/attendance/get";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/attendance/get";
    }
}
