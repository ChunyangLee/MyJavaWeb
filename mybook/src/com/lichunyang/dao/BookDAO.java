package com.lichunyang.dao;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    boolean addBook(Book book) throws SQLException;

    List<Book> queryByBookName(String name) throws SQLException;

    boolean removeByBookName(String name) throws SQLException;

    boolean updateBookByName(Book book, String name) throws SQLException;

    List<Book> queryBooks() throws SQLException;

    int updateBooksByBatch(String name) throws SQLException;

    Page queryForPage(Integer pageNo, Integer pageSize) throws SQLException;

    List<Book> queryBooksByPrice(Double min, Double max) throws SQLException;

    Page<Book> queryBooksByPriceLimit(Integer pageNo, Integer pageSize, Double min, Double max) throws SQLException;
}
