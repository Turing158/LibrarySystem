package com.libSystem.service;

import com.libSystem.dao.BookDaoImpl;
import com.libSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDaoImpl bookDao;

    public String bookList(
            HttpSession session,
            int page){
        if(page <= 0){
            page = 1;
        }
        List<ShowBook> books = bookDao.findAll((page-1)*10);
        session.setAttribute("books",books);
        Page pageBook = new Page();
        pageBook.setPageNow(page);
        int count = bookDao.countAll();
        pageBook.setEnd((count+9)/10);
        session.setAttribute("page",pageBook);
        return "success";
    }

    public String userBookList(
            HttpSession session,
            int page
    ){
        User user = (User) session.getAttribute("user");
        List<UserBook> books = bookDao.findUserBook(user.getUser_id(),(page-1)*10);
        session.setAttribute("userBooks",books);
        Page pageBook = new Page();
        pageBook.setPageNow(page);
        int count = bookDao.countUserBook(user.getUser_id());
        pageBook.setEnd((count+9)/10);
        session.setAttribute("page",pageBook);
        return "success";
    }





//  根据名字搜索书
    public Result findBook(String name,int page){
        Result result = new Result();
        ShowBook book = bookDao.findBook(name,page);
        result.setObject(book);
        if(book != null){
            result.setStatus("success");
            return result;
        }
        result.setStatus("error-exist");
        return result;
    }

//    获取书类型
    public String getBook_type(
            HttpSession session
    ){
        session.setAttribute("bookType",bookDao.findAllBookType());
        return "success";
    }

//    添加新书
    public String insertBook(Book book,HttpSession session){
        session.setAttribute("editBookInfo",book);
        List<BookType> types = bookDao.findAllBookType();
        for (int i = 0; i < types.size(); i++) {
            BookType bookType = types.get(i);
            if(book.getBook_type().equals(bookType.getType_id())){
                session.setAttribute("editBookInfoTypeName",bookType.getType_name());
                break;
            }
        }
        int exist = bookDao.existBook(book.getBook_id());
        if(allPropertiesNotNull(book)){
            if(exist == 0){
                String date = book.getBook_date();
                book.setBook_date(date.substring(0,10));
                bookDao.insertBook(book);
                session.removeAttribute("editBookInfoTypeName");
                session.removeAttribute("editBookInfo");

                session.setAttribute("tips","添加成功!");
                return "success";
            }
            session.setAttribute("tips","该书号已存在!");
            return "error-exist";
        }
        session.setAttribute("tips","请填写完整信息!");
        return "error-null";
    }


//    更新书
    public String updateBook(Book book,HttpSession session){
        if(bookDao.existBook(book.getBook_id()) == 1){
            bookDao.updateBook(book);
            session.setAttribute("tips","更新成功");
            session.removeAttribute("editBookInfoTypeName");
            session.removeAttribute("editBookInfo");
            return "success";
        }
        session.setAttribute("tips","该书不存在");
        return "error";
    }

//    删除书
    public String deleteBook(String id,HttpSession session){
        if(bookDao.existBook(id) == 1){
            if(bookDao.existLogByBook(id) == 0){
                bookDao.deleteBook(id);
                session.setAttribute("tips","删除成功");
                return "success";
            }
            session.setAttribute("tips","该书有借阅记录，无法删除");
            return "error";
        }
        session.setAttribute("tips","该书不存在");
        return "error";
    }


//    借书业务
    public String borrowBook(String bookId,HttpSession session){
        User user = (User) session.getAttribute("user");
        Book book = bookDao.findBookById(bookId);
        if(book.getBook_count() == 0){
            session.setAttribute("tips","书已经借完");
            return "error";
        }
        BookLog bookLog = new BookLog();
        bookLog.setUser_id(user.getUser_id());
        bookLog.setBook_id(bookId);
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.getYear()+"-"+localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+" "+localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();
        bookLog.setLog_date(date);
        bookLog.setLog_status(1);
        bookDao.borrowBook(bookLog);
        bookDao.updateBookCount(book.getBook_id(),book.getBook_count()-1);
        session.setAttribute("tips","借书成功");
        return "success";
    }

//  还书业务
    public String backBook(String book_id,String date,HttpSession session){
        User user = (User) session.getAttribute("user");
        bookDao.backBook(book_id, user.getUser_id() , date);
        Book book = bookDao.findBookById(book_id);
        bookDao.updateBookCount(book.getBook_id(),book.getBook_count()+1);
        session.setAttribute("tips","还书成功");
        return "success";
    }

    public String editBookPage(String id,HttpSession session){
        session.setAttribute("editBookInfo",bookDao.findBookById(id));
        List<BookType> types = bookDao.findAllBookType();
        session.setAttribute("bookType",types);
        for (int i = 0; i < types.size(); i++) {
            BookType bookType = types.get(i);
            if(bookDao.findBookById(id).getBook_type().equals(bookType.getType_id())){
                session.setAttribute("editBookInfoTypeName",bookType.getType_name());
                break;
            }
        }
        return "success";
    }

    public boolean allPropertiesNotNull(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) {
                    return false; // 如果发现任何一个属性为空，则返回false
                }
            } catch (IllegalAccessException e) {
                // 处理IllegalAccessException异常
                e.printStackTrace();
            }
        }

        return true; // 如果所有属性都不为空，则返回true
    }
}
