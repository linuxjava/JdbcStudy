package cn.itcast.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static String url = "";
    private static String username = "";
    private static String pwd = "";

    static {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");

            properties.load(new FileReader(resource.getPath()));

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            pwd = properties.getProperty("password");
            String driver = properties.getProperty("driver");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, pwd);
    }

    public static void close(Statement st, Connection connection) {
        close(null, st, connection);
    }

    public static void close(ResultSet rs, Statement st, Connection connection) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (st != null && !st.isClosed()) {
                st.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
