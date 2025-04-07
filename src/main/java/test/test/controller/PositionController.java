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
import test.test.entity.Position;
import test.test.repository.PositionRepo;
import test.test.service.PositionService;

@Controller
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    @Autowired
    private final PositionRepo positionRepo;

    private final PositionService service;

    @GetMapping("/get")
    public String getAll(Model model) {
        Position item = new Position();
        model.addAttribute("item", item);
        List<Position> items = positionRepo.findAll();
        model.addAttribute("items", items);
        return "gdAdmins/position";
    }

    @PostMapping("/create")
    public String create( Position p, RedirectAttributes redirectAttributes) {
        try {
            if (p.getPositionName().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng tên chức vụ!");
            } 
            else {
                service.create(p);
                redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }
        return "redirect:/positions/get";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Position position = service.getById(id);
            if (position == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy chức vụ!");
            } else {
                model.addAttribute("item", position); // Đổ dữ liệu vào attribute "item" đã có của form
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }
        List<Position> items = positionRepo.findAll();
        model.addAttribute("items", items); // Cần truyền lại danh sách items để hiển thị bảng
        return "gdAdmins/position"; // Trả về view hiện tại
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("item") Position p, RedirectAttributes redirectAttributes) {
        try {
            service.update(p.getPositionID(), p); // Truyền ID từ đối tượng p
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/positions/get";
        } catch (Exception e) {
            System.out.println("lỗi : "+e);
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật!");
            return "redirect:/positions/get"; // Hoặc quay lại form
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteChucVu(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/positions/get";
    }
}
