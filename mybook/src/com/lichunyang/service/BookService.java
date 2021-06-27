package com.lichunyang.service;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void addBook(Book book) throws SQLException;

    void delteBookByName(String name) throws SQLException;

    void updateBookByName(Book book, String name) throws SQLException;

    List<Book> queryBookByName(String name) throws SQLException;

    List<Book> showBooks() throws SQLException;

    Page<Book> showPage(Integer pageNo, Integer pageSize) throws SQLException;

    List<Book> queryBookByPrice(Double min, Double max) throws SQLException;

    Page<Book> showPage(Integer pageNo, Integer pageSize, Double min, Double max) throws SQLException;

}
