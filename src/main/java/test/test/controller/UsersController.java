package test.test.controller;

import java.util.List;


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
import test.test.entity.Users;
import test.test.repository.PositionRepo;
import test.test.repository.UsersRepo;
import test.test.service.UsersService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

  
    private final UsersRepo usersRepo;


    private final PositionRepo positionRepo;

    private final UsersService usersService;

    @GetMapping("/get")
    public String getAllUsers(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        List<Position> items1 = positionRepo.findAll();
        model.addAttribute("items1", items1); 
        List<Users> users = usersRepo.findAll();
        model.addAttribute("users", users);
        return "gdAdmins/users";
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Users> getUser(@PathVariable Integer id) {
    //     return usersService.getUserById(id)
    //             .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    // }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Users user = usersService.getById(id);
            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy nhân viên!");
            } else {
                model.addAttribute("user", user);
                model.addAttribute("items1", positionRepo.findAll()); // Đổ dữ liệu vào attribute "item" đã có của form
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }
        List<Users> users = usersRepo.findAll();
        model.addAttribute("users", users); // Cần truyền lại danh sách items để hiển thị bảng
        return "gdAdmins/users"; // Trả về view hiện tại
    }

    @PostMapping("/create")
    public String create(Users user, RedirectAttributes redirectAttributes) {
        try {
            if (user.getFullName().isEmpty()|| user.getDateOfBirth()== null) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng nhập thông tin!");
            } 
            else {
                usersService.createUser(user);
                redirectAttributes.addFlashAttribute("success", "Thêm mới thành công!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra, vui lòng thử lại sau!");
        }
        return "redirect:/users/get";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Users user, RedirectAttributes redirectAttributes) {
        try {
            Users existingUser = usersService.getById(user.getUserID());
            if (existingUser == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy người dùng!");
                return "redirect:/users/get";
            }

            // Kiểm tra ID chức vụ
            if (user.getPosition() != null && user.getPosition().getPositionID() != null) {
                Position position = positionRepo.findById(user.getPosition().getPositionID())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
                user.setPosition(position);
            }

            // Cập nhật mật khẩu
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }

            usersService.updateUser(user.getUserID(), user);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:/users/get";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật!");
            return "redirect:/users/get";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return "redirect:/users/get";
    }
}
