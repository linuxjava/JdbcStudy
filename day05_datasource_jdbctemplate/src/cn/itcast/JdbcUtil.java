package cn.itcast;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    private static DataSource ds;

    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(resourceAsStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JdbcTemplate createJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    public static void close(Statement st, Connection conn){
        close(null, st, conn);
    }

    public static void close(ResultSet rs, Statement st, Connection conn){
        try {
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
            if(st != null && !st.isClosed()){
                st.close();
            }
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
