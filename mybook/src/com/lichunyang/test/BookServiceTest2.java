package com.lichunyang.test;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;
import com.lichunyang.service.impl.BookServiceimpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest2 {

    @Test
    public void showPage() {
        BookServiceimpl bsi = new BookServiceimpl();

        Page<Book> page = bsi.showPage(1, 4, 30.0, 40.0);
        System.out.println(page);
    }
}