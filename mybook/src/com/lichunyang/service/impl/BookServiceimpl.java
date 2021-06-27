package com.lichunyang.service.impl;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;
import com.lichunyang.dao.impl.BookDAOimpl;
import com.lichunyang.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceimpl implements BookService {
    BookDAOimpl bdi= new BookDAOimpl();

    @Override
    public void addBook(Book book) throws SQLException {
         bdi.addBook(book);
    }

    @Override
    public void delteBookByName(String name)throws SQLException {
        bdi.removeByBookName(name);
    }

    @Override
    public void updateBookByName(Book book, String name)throws SQLException {
          bdi.updateBookByName(book, name);
    }

    @Override
    public List<Book> queryBookByName(String name)throws SQLException {
        return  bdi.queryByBookName(name);
    }

    @Override
    public List<Book> showBooks()throws SQLException {
       return bdi.queryBooks();
    }

    @Override
    public Page<Book> showPage(Integer pageNo, Integer pageSize)throws SQLException {
        return bdi.queryForPage(pageNo, pageSize);
    }

    @Override
    public List<Book> queryBookByPrice(Double min, Double max)throws SQLException {
        return bdi.queryBooksByPrice(min,max);
    }

    @Override
    public Page<Book> showPage(Integer pageNo, Integer pageSize, Double min, Double max)throws SQLException {
        return bdi.queryBooksByPriceLimit(pageNo,pageSize,min,max);
    }
}
