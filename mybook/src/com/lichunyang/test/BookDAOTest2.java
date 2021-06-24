package com.lichunyang.test;

import com.lichunyang.bean.Page;
import com.lichunyang.dao.impl.BookDAOimpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookDAOTest2 {

    @Test
    public void queryForPage() {
        BookDAOimpl bdi = new BookDAOimpl();
        Page page = bdi.queryForPage(1, 4);
        page.getBookList().forEach(System.out::println);
        System.out.println(page.getPageNo());
        System.out.println(page.getPageSize());
        System.out.println(page.getPageTotalItems());
        System.out.println(page.getPageTotalNo());
    }
    @Test
    public void testBatchInsert() {
        BookDAOimpl bdi = new BookDAOimpl();
         int result = bdi.updateBooksByBatch("时间简史");
        System.out.println(result);
    }
}