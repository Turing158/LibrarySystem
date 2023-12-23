package com.libSystem.controller;

import com.libSystem.entity.Result;
import com.libSystem.entity.User;
import com.libSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String userList(
            HttpSession session,
            @RequestParam(defaultValue = "1",required = false) int page
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        userService.userList(session,page);
        return "userList";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(
            @RequestParam String user,
            @RequestParam String password,
            HttpSession session
    ){
        String status = userService.userLogin(user,password,session);
        if(status.equals("success")){
            return "redirect:/home";
        }
        if(status.equals("error-password")){
            session.setAttribute("loginTips","密码错误！");
            return "redirect:/login";
        }
        session.setAttribute("loginTips","用户不存在！");
        return "redirect:/login";
    }
}
