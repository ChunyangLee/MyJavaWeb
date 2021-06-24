package com.lichunyang.dao;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;

import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);

    List<Book> queryByBookName(String name);

    boolean removeByBookName(String name);

    boolean updateBookByName(Book book, String name);

    List<Book> queryBooks();

    int updateBooksByBatch(String name);

    Page queryForPage(Integer pageNo, Integer pageSize);

    List<Book> queryBooksByPrice(Double min, Double max);

    Page<Book> queryBooksByPriceLimit(Integer pageNo, Integer pageSize, Double min, Double max);
}
