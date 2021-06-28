package com.lichunyang.dao.impl;

import com.lichunyang.bean.User;
import com.lichunyang.dao.BaseDAO;
import com.lichunyang.dao.UserDAO;
import com.lichunyang.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl extends BaseDAO<User> implements UserDAO {
    @Override
    public boolean saveUser(User user) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into  t_user(username, password, email) values(?,?,?)";
        int flag = update(con,sql,user.getUsername(),user.getPassword(),user.getEmail());
        if(flag!=-1) return true;
        else return false;
    }

    @Override
    public User queryUserByName(String name) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_user where t_user.username=?";
        User bean = getBean(con, sql, name);
//        System.out.println(bean);
        return  bean;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        String sql = "select * from t_user where t_user.username=? and t_user.password=?";
        User bean = getBean(con, sql, name,password);
        return  bean;
    }

    @Override
    public List<User> queryusersByLastName(String lastName) throws SQLException {
        List<User> listUser = new ArrayList<>();
        Connection con = JdbcUtils.getConnection();
        String sql = "SELECT * FROM t_user WHERE INSTR(username,?)<>0";
        listUser = getBeans(con, sql, lastName);
        return listUser;
    }
}
