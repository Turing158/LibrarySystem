package com.libSystem.controller;

import com.libSystem.entity.User;
import com.libSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BookController {
    @Autowired
    BookService bookService;


    @GetMapping("/book")
    public String bookList(
            @RequestParam(defaultValue = "1",required = false) int page,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        bookService.bookList(session,page);
        return "bookList";
    }
    @GetMapping("/editbook")
    public String editBookList(
            @RequestParam(defaultValue = "1",required = false) int page,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        if (user.getUser_permission() != 1){
            return "redirect:/home";
        }
        bookService.bookList(session,page);
        return "editBookList";
    }

    @GetMapping("/userBook")
    public String userBookList(
            HttpSession session,
            @RequestParam(defaultValue = "1",required = false) int page
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        bookService.userBookList(session,page);
        return "userBookList";
    }


    @GetMapping("/borrowBook")
    public String borrowBook(
            @RequestParam String id,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        bookService.borrowBook(id,session);
        return "redirect:/book";
    }
    @GetMapping("/returnBook")
    public String returnBook(
            @RequestParam String id,
            @RequestParam String date,
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        bookService.backBook(id,date,session);
        return "redirect:/book";
    }
    @GetMapping("/clearTips")
    public String clearTips(
            HttpSession session
    ){
        session.removeAttribute("tips");
        return "redirect:/book";
    }
}
