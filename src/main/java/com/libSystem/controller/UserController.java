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
        if (user.getUser_permission() != 1){
            return "redirect:/home";
        }
        userService.userList(session,page);
        return "userList";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }
    @PostMapping("/userLogin")
    public String userLogin(
            @RequestParam String user,
            @RequestParam String password,
            @RequestParam String code,
            HttpSession session
    ){

        String status = userService.userLogin(user,password,code,session);
        if(status.equals("success")){
            return "redirect:/home";
        }
        return "redirect:/login";
    }
    @PostMapping("/userReg")
    public String userReg(
            @RequestParam String user,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String code,
            HttpSession session
    ){
        String status = userService.UserRegister(user,password,name,code,session);
        if(status.equals("success")){
            return "redirect:/login";
        }
        return "redirect:/reg";
    }

    @GetMapping("/editUser")
    public String editUserPage(
            @RequestParam String id,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        userService.editUserPage(id,session);
        return "editUser";
    }
    @PostMapping("/updateUser")
    public String editUser(
            @RequestParam String id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam int permission,
            HttpSession session
    ){
        User userObj = (User) session.getAttribute("user");
        if(userObj == null){
            return "redirect:/login";
        }
        User user = new User();
        user.setUser_id(id);
        user.setUser_name(username);
        user.setUser_password(password);
        user.setUser_permission(permission);
        userService.updateUser(user,session);
        return "redirect:/user";
    }


    @GetMapping("/deleteUser")
    public String deleteUser(
            @RequestParam String id,
            HttpSession session
    ){
        User userObj = (User) session.getAttribute("user");
        if(userObj == null){
            return "redirect:/login";
        }
        userService.deleteUser(id,session);
        return "redirect:/user";
    }
}
