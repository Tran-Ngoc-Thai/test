package test.test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.RequiredArgsConstructor;

import test.test.entity.Users;
import test.test.entity.WorkingHour;
import test.test.repository.UsersRepo;
import test.test.repository.WorkingHoursRepo;
import test.test.service.WorkingHoursService;

@Controller
@RequestMapping("/working-hours")
@RequiredArgsConstructor
public class WorkingHoursController {

    @Autowired
    private final WorkingHoursRepo workingHoursRepo;

    @Autowired
    private final UsersRepo usersRepo;

    private final WorkingHoursService service;

    @GetMapping("/get")
    public String getAll(Model model) {
        WorkingHour wh = new WorkingHour();
        model.addAttribute("wh", wh);
        List<Users> userList = usersRepo.findAll();
        model.addAttribute("userList", userList);
        List<WorkingHour> whList = workingHoursRepo.findAll();
        model.addAttribute("whList", whList);
        return "gdAdmins/workingHours";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            WorkingHour workingHour = service.getById(id);
            if (workingHour == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy chức vụ!");
            } else {
                model.addAttribute("wh", workingHour);
                List<Users> userList = usersRepo.findAll();
                model.addAttribute("userList", userList);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }

        List<WorkingHour> whList = workingHoursRepo.findAll();
        model.addAttribute("whList", whList);
        return "gdAdmins/workingHours";
    }

    @PostMapping("/create")
    public String create(WorkingHour wh,
                        RedirectAttributes redirectAttributes) {
       try {
        if (wh.getCheckInTime()== null || wh.getCheckOutTime() == null || wh.getWorkDate() == null) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
        } else {
            service.create(wh);
            redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
        }
       } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
       }

       

        return "redirect:/working-hours/get";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute WorkingHour wh, RedirectAttributes redirectAttributes) {
        try {
            WorkingHour exWorkingHour = service.getById(wh.getWorkingHourID());
            if (exWorkingHour == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng!");
                return "redirect:/working-hours/get";
            }

            // Kiểm tra ID chức vụ
            if (wh.getUser() != null && wh.getUser().getUserID() != null) {
                Users users = usersRepo.findById(wh.getUser().getUserID())
                    .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
                wh.setUser(users);
            }

            
            service.update(wh.getWorkingHourID(), wh);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/working-hours/get";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật!");
            return "redirect:/working-hours/get";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/working-hours/get";
    }
}
