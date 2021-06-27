package com.lichunyang.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
//    初始化数据库连接池，否则每次getCon都要创建一次；
    static {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//                ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * 获取链接， 放入ThreadLocal中， 此链接以设置为非自动提交事务。
     * @return
     */
    public static Connection getConnection(){
        Connection con = conns.get();
        //如果conns里面还没放连接，则创建一个
        if(con==null){
            try {
                con = dataSource.getConnection();
                conns.set(con);
                con.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            System.out.println("连接的状态是否是关闭"+con.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("使用的连接为"+con);
        return con;
    }

    public static void RollbackAndClose(){
        Connection con = conns.get();
        if(con!=null){
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                DbUtils.closeQuietly(con);
            }
        }
        conns.remove();
    }

    public static void close(){
        Connection con = conns.get();
        if(con!=null){
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbUtils.closeQuietly(con);
        }
        conns.remove();
    }

    public static void commitAndClose(){
        Connection con = conns.get();
        if(con!=null){
            try {
                con.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                DbUtils.closeQuietly(con);
            }
        }
        //移除value，*****
        conns.remove();
    }

    //获得连接
    /**
     *
     * @return 返回null，说明获取连接失败！
     */
//    public static Connection getConnection() {
//        Connection con=null;
//
//        try {
//            con= dataSource.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return con;
//    }

    //关闭连接
//    public static void close(Connection con){
//        if(con!=null){
//            DbUtils.closeQuietly(con);
//        }
//    }

}
