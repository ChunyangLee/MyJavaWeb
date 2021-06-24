package com.lichunyang.dao.impl;

import com.lichunyang.bean.Book;
import com.lichunyang.dao.BaseDAO;
import com.lichunyang.dao.BookDAO;
import com.lichunyang.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class BookDAOimpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public boolean addBook(Book book) {
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        int flag = update(con,sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath()) ;
        JdbcUtils.close(con);
        return flag>0?true:false;
    }

    @Override
    public List<Book> queryByBookName(String name) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_book where name=?";
        List<Book> bookList = getBeans(con, sql, name);
        JdbcUtils.close(con);
        return bookList;
    }

    @Override
    public boolean removeByBookName(String name) {
        Connection con = JdbcUtils.getConnection();
        String sql = "delete from t_book where name=?";
       int flag= update(con,sql,name) ;
        JdbcUtils.close(con);
        return flag>0?true:false;
    }

    @Override
    public boolean updateBookByName(Book book, String name) {
        Connection con = JdbcUtils.getConnection();
        String sql = "UPDATE t_book set name=?, price=?,author=?,sales=?,stock=?,img_path=? WHERE `name`=?";
        int flag = update(con,sql, book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),name);
        JdbcUtils.close(con);
        return flag>0?true:false;
    }

    @Override
    public List<Book> queryBooks() {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_book";
        List<Book> bookList = getBeans(con, sql);
        JdbcUtils.close(con);
        return bookList;
    }
}
