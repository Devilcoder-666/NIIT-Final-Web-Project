package com.my_project.niit_final_project.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class AuthController {
    @GetMapping("login")
    public String login(){
        return "/client/auth/login";
    }
}
