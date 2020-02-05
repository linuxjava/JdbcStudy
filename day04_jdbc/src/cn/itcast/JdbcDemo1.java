package cn.itcast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
        Statement statement = connection.createStatement();
        String sql = "UPDATE account SET balance = balance + 500 WHERE NAME = 'zhangsan'";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        statement.close();
        connection.close();
    }
}
