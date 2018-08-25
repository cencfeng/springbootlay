package com.fashion.mjysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/topage")
public class TopageController {
    @RequestMapping(value = "{pageName1}")
    public ModelAndView toPage(@PathVariable("pageName1") String pageName1) {
        ModelAndView mv = new ModelAndView(pageName1);
        return mv;
    }
}
