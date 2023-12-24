package com.libSystem.service;

import com.libSystem.dao.UserDaoImpl;
import com.libSystem.entity.Page;
import com.libSystem.entity.Result;
import com.libSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDaoImpl userDao;


    public String userList(
            HttpSession session,
            int page
    ){
        List<User> users =  userDao.findAll((page-1)*10);
        session.setAttribute("users",users);
        Page userPage = new Page();
        userPage.setPageNow(page);
        userPage.setEnd((userDao.countUser()+9)/10);
        session.setAttribute("page",userPage);
        return "success";
    }


//    用户登录业务
    public String userLogin(String userId,String password,String code,HttpSession session){
        String codeSession = (String) session.getAttribute("code");
        session.setAttribute("userId",userId);
        session.setAttribute("loginPassword",password);
        if(code.equals(codeSession)){
            if(userDao.existUser(userId) == 1){
                User user = userDao.findUser(userId);
                if(user.getUser_password().equals(password)){
                    session.setAttribute("user",user);
                    session.setAttribute("tips","登录成功！");
                    session.removeAttribute("userId");
                    session.removeAttribute("loginPassword");
                    return "success";
                }
                session.setAttribute("loginTips","密码错误！");
                return "error-password";
            }
            session.setAttribute("loginTips","用户不存在！");
            return "error-user";
        }
        session.setAttribute("loginTips","验证码错误！");
        return "error-code";
    }

//  用户注册业务
    public String UserRegister(String userId,String password,String name,String code,HttpSession session){
        String codeSession = (String) session.getAttribute("code");
        session.setAttribute("regUser",userId);
        session.setAttribute("regPassword",password);
        session.setAttribute("regName",name);
        if(code.equals(codeSession)){
            if(userDao.existUser(userId) == 0){
                int status = userDao.insertUser(new User(userId,name,password,0));
                if(status == 1){
                    session.removeAttribute("regUser");
                    session.removeAttribute("regPassword");
                    session.removeAttribute("regName");
                    session.setAttribute("loginTips","注册成功，请登录！");
                    return "success";
                }
                session.setAttribute("regTips","注册失败！");
                return "error";
            }
            session.setAttribute("regTips","用户已存在！");
            return "error-user";
        }
        session.setAttribute("regTips","验证码错误！");
        return "error-code";
    }

    public String editUserPage(String id,HttpSession session){
        User user = userDao.findUser(id);
        session.setAttribute("editUser",user);
        return "success";
    }

//    更新用户信息
    public String updateUser(User user,HttpSession session){
        userDao.updateUser(user);
        session.setAttribute("tips","更新用户信息成功！");
        session.removeAttribute("editUser");
        return "success";
    }

//    删除用户
    public String deleteUser(String user,HttpSession session){
        if(userDao.existLogByUser(user) == 0){
            userDao.deleteUser(user);
            session.setAttribute("tips","删除用户成功！");
            return "success";
        }
        session.setAttribute("tips","此用户有借书记录，无法删除！");
        return "error";
    }

}
