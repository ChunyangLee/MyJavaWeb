package com.lichunyang.service.impl;

import com.lichunyang.bean.User;
import com.lichunyang.dao.UserDAO;
import com.lichunyang.dao.impl.UserDAOimpl;
import com.lichunyang.service.UserService;

//业务层处理登陆和注册，需要和数据库交互，
//UserServiceimpl 相当于办业务的柜台，每个方法里面怎么办的通过UserDAO办的，
public class UserServiceimpl implements UserService {

    private UserDAO udao = new UserDAOimpl();

    @Override
    public boolean registUser(User user) {
        return udao.saveUser(user);
    }

    @Override
    public boolean login(String username, String password) {
        User user = udao.queryUserByNameAndPassword(username, password);
        if(user!=null) return true;
        else
            return false;
    }

    @Override
    public boolean existsUsername(String username) {
        User user = udao.queryUserByName(username);
        if(user!=null) return true;
        else
            return false;
    }
}
