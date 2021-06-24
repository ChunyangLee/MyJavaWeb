package com.lichunyang.test;

import com.lichunyang.bean.Book;
import com.lichunyang.dao.impl.BookDAOimpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDAOTest {

    @Test
    public void addBook() {
        BookDAOimpl bdi = new BookDAOimpl();
        boolean b = bdi.addBook(new Book(1, "时间见识",  "霍金",12.0, 34, 1111, "static/img/default.jpg"));
        System.out.println(b);
    }

    @Test
    public void queryByBookName() {
        BookDAOimpl bdi = new BookDAOimpl();
        List<Book> bookList = bdi.queryByBookName("时间见识");
        System.out.println(bookList);
    }

    @Test
    public void removeByBookName() {
        BookDAOimpl bdi = new BookDAOimpl();
        System.out.println( bdi.removeByBookName("时间见识"));
    }

    @Test
    public void updateBookByName() {
        BookDAOimpl bdi = new BookDAOimpl();
        System.out.println(bdi.updateBookByName(new Book(), "时间见识"));

    }
}