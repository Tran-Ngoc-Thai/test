package test.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(Model mode, HttpSession session) {

        String role = (String) session.getAttribute("role");
        System.out.println("role: " + role);
        return "gdUsers/home";
    }
}
