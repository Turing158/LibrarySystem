package com.libSystem.controller;

import com.libSystem.entity.RequestParam;
import com.libSystem.entity.Result;
import com.libSystem.entity.User;
import com.libSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user){
        return userService.userLogin(user.getUser_id(),user.getUser_password());
    }

    @PostMapping("/reg")
    @ResponseBody
    public Result register(@RequestBody User user){
        return userService.UserRegister(user.getUser_id(),user.getUser_password());
    }

    @PostMapping("/countUser")
    @ResponseBody
    public Result countUser(){
        return userService.countUser();
    }

    @PostMapping("/getUser")
    @ResponseBody
    public Result findAllUser(@RequestBody RequestParam requestParam){
        return userService.findAllUser(requestParam.getPage());
    }


    @PostMapping("/updatePermission")
    @ResponseBody
    public Result updateUserPermission(@RequestBody RequestParam requestParam){
        return userService.updateUserPermission(requestParam.getUser_id(),requestParam.getPermission());
    }


    @PostMapping("/delUser")
    @ResponseBody
    public Result deleteUser(@RequestBody RequestParam requestParam){
        return userService.deleteUser(requestParam.getUser_id());
    }
}
