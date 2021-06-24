package com.lichunyang.service;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void delteBookByName(String name);

    void updateBookByName(Book book, String name);

    List<Book> queryBookByName(String name);

    List<Book> showBooks();

    Page<Book> showPage(Integer pageNo, Integer pageSize);

    List<Book> queryBookByPrice(Double min, Double max);

    Page<Book> showPage(Integer pageNo, Integer pageSize, Double min, Double max);

}
