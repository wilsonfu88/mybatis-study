package cn.weisoft.mybatisstudy.service;

import cn.weisoft.mybatisstudy.entity.Book;
import cn.weisoft.mybatisstudy.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;


    public Book selectBookById(int id){
        return bookMapper.selectBookById(id);
    }

    public List<Book> selectAllBooks(){
        return bookMapper.selectAllBooks();
    }

    public Book selectBookByIdProc(int id){
        return bookMapper.selectBookByIdProc(id);
    }

    public Book insertBookByProc(Book book){
        int i = bookMapper.insertBookByProc(book);
        System.out.println("insertBookByProc=" + i);
        return book;
    }

    public Book insertBookBySQL(Book book){
        int i = bookMapper.insertBookBySQL(book);
        System.out.println("insertBookBySQL=" + i);
        return book;
    }

    public List<Book>selectWithParam(Map<String,Object> param){
        return bookMapper.selectWithParam(param);
    }


    public PageInfo<Book> getBooksPage(int page, int size){
        PageHelper.startPage(page, size);
        List<Book> listBooks = bookMapper.selectAllBooks();
        PageInfo<Book> pageInfoDemo = new PageInfo<Book>(listBooks);
        return pageInfoDemo;
    }
}
