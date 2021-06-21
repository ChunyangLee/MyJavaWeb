package com.lichunyang.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    private QueryRunner qr = new QueryRunner();
    private Class<T> aClass;


    {
        Type gs = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) gs).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        aClass = (Class<T>) actualTypeArgument;
    }

    /**
     *     更新操作，包括增，删，改 create， remove， update
     * @param sql
     * @param args
     * @return 返回-1说明，执行失败，返回其他表示影响的行数
     */
    public int update( Connection con, String sql, Object ... args){
        int updateCount=-1;
        try {
             updateCount = qr.update(con, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateCount;
    }

    /**
     *  获取一个对象方法，
     * @param con
     * @param sql
     * @param args
     * @return
     */
    public T getBean(Connection con, String sql, Object ...args){
        T t=null;
        ResultSetHandler rsh = new BeanHandler(aClass);
        try {
            t= (T) qr.query(con, sql, rsh, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    /**
     *  获取对象列表， 查询结果为多行，
     * @param con
     * @param sql
     * @param args
     * @return
     */
    public List<T> getBeans(Connection con, String sql, Object ... args){
        List<T> beanList = null;
        try {
            beanList = qr.query(con, sql, new BeanListHandler<T>(aClass), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  beanList;
    }

    public Object getValue(Connection con, String sql, Object ... args){
        Object o = null;
        try {
            o=qr.query(con,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return o;
    }


}
