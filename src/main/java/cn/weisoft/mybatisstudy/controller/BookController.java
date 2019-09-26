package cn.weisoft.mybatisstudy.controller;

import cn.weisoft.mybatisstudy.entity.Book;
import cn.weisoft.mybatisstudy.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/selectBookById")
    public Book selectBookById(int id) {
        return bookService.selectBookById(id);
    }

    @RequestMapping("/selectAllBooks")
    public List<Book> selectAllBooks() {
        return bookService.selectAllBooks();
    }

    @RequestMapping("/selectBookByIdProc")
    public Book selectBookByIdProc(int id) {
        return bookService.selectBookByIdProc(id);
    }

    @RequestMapping("/insertBookByProc")
    public Book insertBookByProc() {
        Book book = new Book();
        book.setName("JAVA" + UUID.randomUUID().toString());
        book.setAuthor("WILSON.FU0908" + new Date());
        book.setPublishDate(new Date());
        return bookService.insertBookByProc(book);
    }

    @RequestMapping("/insertBookBySQL")
    public Book insertBookBySQL() {
        Book book = new Book();
        book.setName("JAVA" + UUID.randomUUID().toString());
        book.setAuthor("wilson.fu-0908" + new Date());
        book.setPublishDate(new Date());
        return bookService.insertBookBySQL(book);
    }

    @RequestMapping("/selectWithParam")
    public List<Book> selectWithParam() {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("id",5);
        //param.put("author","傅威01");
        param.put("name","%入门%");

        Integer intA = Integer.valueOf(128);
        Integer intB = Integer.valueOf(128);

        System.out.println("intA==intB:" + (intA==intB));


        Integer intC = Integer.valueOf(2);
        Integer intD = Integer.valueOf(2);

        System.out.println("intC==intD:" + (intC==intD));
        return bookService.selectWithParam(param);
    }

    @RequestMapping("/getBooksPage")
    public PageInfo<Book> getBooksPage(int page, int size) {
        return bookService.getBooksPage(page,size);
    }
}
