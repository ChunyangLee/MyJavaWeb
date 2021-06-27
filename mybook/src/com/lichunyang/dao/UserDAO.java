package com.lichunyang.dao;

import com.lichunyang.bean.User;

import java.sql.SQLException;
import java.util.List;

//指定对于User操作的规范，
//在该类中，control + shift + t自动生成测试类
public interface UserDAO{

    boolean saveUser(User user) throws SQLException;

    User queryUserByName(String name)throws SQLException ;

    User queryUserByNameAndPassword(String name, String password)throws SQLException ;

    List<User> queryusersByLastName(String lastName)throws SQLException ;
}
