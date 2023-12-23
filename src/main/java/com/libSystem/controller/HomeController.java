package com.libSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping(value = {"/","/home"})
    public String home(){
        return "index";
    }
    @RequestMapping("/exit")
    public String exit(
            HttpSession session
    ){
        session.removeAttribute("user");
        return "redirect:/home";
    }
}
