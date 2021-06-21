package com.lichunyang.dao;

import com.lichunyang.bean.User;

import java.util.List;

//指定对于User操作的规范，
//在该类中，control + shift + t自动生成测试类
public interface UserDAO{

    boolean saveUser(User user);

    User queryUserByName(String name);

    User queryUserByNameAndPassword(String name, String password);

    List<User> queryusersByLastName(String lastName);
}
