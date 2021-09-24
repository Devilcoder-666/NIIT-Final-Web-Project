package com.my_project.niit_final_project.controllers.client;

import com.my_project.niit_final_project.entities.User;
import com.my_project.niit_final_project.services.UserRoleService;
import com.my_project.niit_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;





    @GetMapping("login")
    public String login(){
        return "/client/auth/login";
    }
    @GetMapping("/logout")
    public  String logout(){
        return "/client/home";
    }
    @GetMapping("/signup")
    public  String signup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles",userRoleService.getAll());
        return "/client/auth/signup";
    }
        @PostMapping("/do-signup")
        public  String doSignup(User user){
            userService.save(user);
            return "redirect:/login";
        }
}
