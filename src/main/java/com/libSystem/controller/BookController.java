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
        bookService.bookList(session,page);
        return "editBookList";
    }
}
