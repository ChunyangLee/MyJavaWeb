package com.lichunyang.dao.impl;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;
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

    @Override
    public int updateBooksByBatch(String name) {
        Connection con = JdbcUtils.getConnection();
        String sql="insert into t_book(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        Object[][] params = new Object[20][];
        for (int i = 0; i < params.length; i++) {
            params[i]=new Object[]{name+i,24.5+i,"霍金"+i,25+i,170+i,"static/img/default.jpg"};
        }
        int result = updateByBatch(con,sql,params);
        JdbcUtils.close(con);
        return result;
    }

    // 最好每个查询语句写一个dao方法，对page的设置可以在BookService层完成。
    // dao层只负责查询service层需要的数据。不做其他的事， 具体业务实现放在service层进行。
    @Override
    public Page<Book> queryForPage(Integer pageNo, Integer pageSize) {
        Connection con = JdbcUtils.getConnection();
        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);
        String sql = "select count(*) from t_book";
        page.setPageTotalItems( ((Long)getValue(con,sql)).intValue());
        page.setPageTotalNo(page.getPageTotalItems()%pageSize>0?page.getPageTotalItems()/pageSize+1:page.getPageTotalItems()/pageSize);
        page.setPageNo(pageNo);
        String sql2 = "select * from t_book limit ?, ?";
        //这里要用新设置的pageNo，如果用pageNo的话，是形参传过来的，如果是非法的很大的数就查不到图书数据了
        page.setBookList(getBeans(con,sql2,(page.getPageNo()-1)*pageSize,pageSize));
        JdbcUtils.close(con);

        return page;
    }

    @Override
    public List<Book> queryBooksByPrice(Double min, Double max) {
        Connection con = JdbcUtils.getConnection();
        String sql="select * from t_book where price>=? and price<=?";
        List<Book> books = getBeans(con, sql, min, max);
        JdbcUtils.close(con);
        return books;
    }

    @Override
    public Page<Book> queryBooksByPriceLimit(Integer pageNo, Integer pageSize, Double min, Double max) {
        Connection con = JdbcUtils.getConnection();

        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);
        String sql = "select count(*) from t_book where price>=? and price<=?";
        page.setPageTotalItems( ((Long)getValue(con,sql,min,max)).intValue());
        page.setPageTotalNo(page.getPageTotalItems()%pageSize>0?page.getPageTotalItems()/pageSize+1:page.getPageTotalItems()/pageSize);
        page.setPageNo(pageNo);
        String sql2 = "select * from t_book where price>=? and price<=? limit ?,?";
        //这里要用新设置的pageNo，如果用pageNo的话，是形参传过来的，如果是非法的很大的数就查不到图书数据了
        page.setBookList(getBeans(con,sql2,min,max,(page.getPageNo()-1)*pageSize,pageSize));

        JdbcUtils.close(con);

        return page;
    }
}
