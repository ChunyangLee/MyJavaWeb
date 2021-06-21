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

    //获得连接

    /**
     *
     * @return 返回null，说明获取连接失败！
     */
    public static Connection getConnection() {
        Connection con=null;

        try {
            con= dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
    //关闭连接
    public static void close(Connection con){
        if(con!=null){
            DbUtils.closeQuietly(con);
        }
    }

}
