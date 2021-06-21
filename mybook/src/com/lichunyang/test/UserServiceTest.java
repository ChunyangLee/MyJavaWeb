package com.lichunyang.test;

import com.lichunyang.bean.User;
import com.lichunyang.service.impl.UserServiceimpl;
import org.junit.Test;

import java.util.List;


public class UserServiceTest {

    @Test
    public void registUser() {
        UserServiceimpl usi = new UserServiceimpl();
        System.out.println(usi.registUser(new User(3,"Cat","123","cat@qq.com")));

    }

    @Test
    public void login() {
        UserServiceimpl usi = new UserServiceimpl();
        if(usi.login("Cat", "123")){
            System.out.println("登陆成功！");
        }else{
            System.out.println("登陆失败，");
        }

    }

    @Test
    public void existsUsername() {
        UserServiceimpl usi = new UserServiceimpl();
        System.out.println(usi.existsUsername("lcy168"));
    }
    @Test
    public void test(){
        UserServiceimpl usi = new UserServiceimpl();
        List<User> list = usi.getUsersByLastName("张");
        list.forEach(System.out::println);

        System.out.println(usi.existsUsername("张三"));


    }
}