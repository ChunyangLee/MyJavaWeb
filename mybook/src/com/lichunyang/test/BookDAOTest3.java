package com.lichunyang.test;

import com.lichunyang.dao.impl.BookDAOimpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookDAOTest3 {

    @Test
    public void queryBooksByPrice() {
        BookDAOimpl bdi = new BookDAOimpl();
        System.out.println(bdi.queryBooksByPrice(30.0, 40.0));
    }
}