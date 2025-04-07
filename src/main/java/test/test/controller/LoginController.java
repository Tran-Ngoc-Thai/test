package test.test.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import test.test.entity.Users;
import test.test.repository.UsersRepo;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsersRepo UsersRepo;

    @RequestMapping("/")
    public String logginPge(){
        return "gdUsers/dangnhap";
    }

     @PostMapping("/")
    public String login(Model model, HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tài khoản!");
            return "gdUsers/dangnhap";
        }
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập mật khẩu!");
            return "gdUsers/dangnhap";
        }

        Users user = UsersRepo.findByPhoneOrEmail(name, name);
        if (user == null) {
            model.addAttribute("error", "Tài khoản không tồn tại!");
            return "gdUsers/dangnhap";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Mật khẩu không chính xác!");
            return "gdUsers/dangnhap";
        }

        if (!user.isActive()) {
            model.addAttribute("error", "Tài khoản đã bị vô hiệu hóa!");
            return "gdUsers/dangnhap";
        }


        String role = "user"; // Mặc định là user
        role = (user.getRole() != null && user.getRole()) ? "admin" : "user";        

        System.out.println("role: " + role);

        // Lưu thông tin vào session
        session.setAttribute("loggedInUser", user);
        session.setAttribute("username", user.getFullName()); // Đảm bảo tồn tại
        session.setAttribute("role", role);

        logger.info("User logged in: {}", user.getFullName());
        logger.info("Assigned role: {}", role);
        logger.info("Session saved - username: {}", session.getAttribute("username"));


        // String redirectUri = (String) session.getAttribute("redirectAfterLogin");

        // System.out.println("redirectUri: " + redirectUri);

        // if (redirectUri != null && !redirectUri.equals("/dangnhap")) {
        //     session.removeAttribute("redirectAfterLogin");
        //     return "redirect:" + redirectUri;
        // }
        // Chuyển hướng đến trang chính
        if (role.equals("admin")) {
            return "redirect:/home";
        }else{
            return "redirect:/home";
        }


    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("Logging out user: {}", session.getAttribute("username"));
        
        session.removeAttribute("loggedInUser");
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.invalidate();
        
        return "redirect:/";
    }
}
