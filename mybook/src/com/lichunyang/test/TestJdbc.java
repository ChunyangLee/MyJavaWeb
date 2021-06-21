package com.lichunyang.test;

import com.lichunyang.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestJdbc {
    @Test
    public void test() throws SQLException {

        for (int i = 0; i < 100; i++) {
            Connection con = JdbcUtils.getConnection();
            System.out.println(con);
            JdbcUtils.close(con);

        }

    }


}
