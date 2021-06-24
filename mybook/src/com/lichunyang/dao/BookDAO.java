package com.lichunyang.dao;

import com.lichunyang.bean.Book;

import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);

    List<Book> queryByBookName(String name);

    boolean removeByBookName(String name);

    boolean updateBookByName(Book book, String name);

    List<Book> queryBooks();
}
