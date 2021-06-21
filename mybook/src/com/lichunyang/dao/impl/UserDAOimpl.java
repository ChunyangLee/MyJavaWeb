package com.lichunyang.dao.impl;

import com.lichunyang.bean.User;
import com.lichunyang.dao.BaseDAO;
import com.lichunyang.dao.UserDAO;
import com.lichunyang.utils.JdbcUtils;

import java.sql.Connection;

public class UserDAOimpl extends BaseDAO<User> implements UserDAO {
    @Override
    public boolean saveUser(User user) {
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into  t_user(username, password, email) values(?,?,?)";
        int flag = update(con,sql,user.getUsername(),user.getPassword(),user.getEmail());
        JdbcUtils.close(con);
        if(flag!=-1) return true;
        else return false;
    }

    @Override
    public User queryUserByName(String name) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_user where t_user.username=?";
        User bean = getBean(con, sql, name);
        JdbcUtils.close(con);
        return  bean;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_user where t_user.username=? and t_user.password=?";
        User bean = getBean(con, sql, name,password);
        JdbcUtils.close(con);
        return  bean;
    }
}
