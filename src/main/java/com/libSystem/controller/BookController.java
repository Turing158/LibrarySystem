package com.libSystem.controller;

import com.libSystem.entity.Book;
import com.libSystem.entity.RequestParam;
import com.libSystem.entity.Result;
import com.libSystem.entity.ShowBook;
import com.libSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    BookService bookService;


    @RequestMapping("/book")
    public String bookList(){
        return "bookList";
    }

    @PostMapping("/findAllBook")
    @ResponseBody
    public Result findAllBook(@RequestBody RequestParam requestParam){
        return bookService.findAllBook(requestParam.getPage());
    }

    @PostMapping("/countBook")
    @ResponseBody
    public Result countBook(){
        return bookService.countBook();
    }


    @PostMapping("/findBook")
    @ResponseBody
    public Result findBook(@RequestBody RequestParam requestParam){
        return bookService.findBookById(requestParam.getBook_id());
    }

    @PostMapping("/delBook")
    @ResponseBody
    public Result deleteBook(@RequestBody RequestParam requestParam){
        return bookService.deleteBook(requestParam.getBook_id());
    }

    @PostMapping("/countUserBook")
    @ResponseBody
    public Result countUserBook(@RequestBody RequestParam requestParam){
        return bookService.countUserBook(requestParam.getUser_id());
    }

    @PostMapping("/borrow")
    @ResponseBody
    public Result borrowBook(@RequestBody RequestParam requestParam){
        return bookService.borrowBook(requestParam.getBook_id(),requestParam.getUser_id());
    }

    @PostMapping("/backBook")
    @ResponseBody
    public Result backBook(@RequestBody RequestParam requestParam){
        return bookService.backBook(requestParam.getBook_id(),requestParam.getUser_id(),requestParam.getDate());
    }

    @PostMapping("/getUserBook")
    @ResponseBody
    public Result findUserBook(@RequestBody RequestParam requestParam){
        return bookService.findUserBook(requestParam.getUser_id(),requestParam.getPage());
    }


    @PostMapping("/updateBook")
    @ResponseBody
    public Result updateBook(@RequestBody ShowBook showBook){
        return bookService.updateBook(showBook);
    }

    @PostMapping("/insertBook")
    @ResponseBody
    public Result insertBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }


}
