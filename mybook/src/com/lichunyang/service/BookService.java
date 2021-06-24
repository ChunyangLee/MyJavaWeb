package com.lichunyang.service;

import com.lichunyang.bean.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void delteBookByName(String name);

    void updateBookByName(Book book, String name);

    List<Book> queryBookByName(String name);

    List<Book> showBooks();
}
