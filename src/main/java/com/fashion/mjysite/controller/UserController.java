package com.fashion.mjysite.controller;

import com.fashion.mjysite.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/login")
    public String login(User user) {

        return "index";
    }
}
