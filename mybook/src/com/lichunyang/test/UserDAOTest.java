package com.lichunyang.test;

import com.lichunyang.bean.User;
import com.lichunyang.dao.impl.UserDAOimpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Test
    public void saveUser() {
        UserDAOimpl userDAOimpl = new UserDAOimpl();
        userDAOimpl.saveUser(new User(2,"Tom","123456","Tom@qq.com"));

    }

    @Test
    public void queryUserByName() {
        UserDAOimpl userDAOimpl = new UserDAOimpl();
        User tom = userDAOimpl.queryUserByName("Tom");
        System.out.println(tom);
    }

    @Test
    public void queryUserByNameAndPassword() {
        UserDAOimpl userDAOimpl = new UserDAOimpl();
        User tom = userDAOimpl.queryUserByNameAndPassword("Tom", "123456");
        System.out.println(tom);
        User admin = userDAOimpl.queryUserByNameAndPassword("admin", "123");
        System.out.println(admin);
    }
}