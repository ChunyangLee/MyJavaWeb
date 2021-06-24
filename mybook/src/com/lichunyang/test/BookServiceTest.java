package com.lichunyang.test;

import com.lichunyang.bean.Book;
import com.lichunyang.service.impl.BookServiceimpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest {

    @Test
    public void addBook() {
        BookServiceimpl bsi = new BookServiceimpl();
        bsi.addBook(new Book(1, "时间见识",  "霍金",12.0, 34, 1111, "static/img/default.jpg"));
    }

    @Test
    public void delteBookByName() {
    }

    @Test
    public void updateBookByName() {
    }

    @Test
    public void queryBookByName() {
    }
}