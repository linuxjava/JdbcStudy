package cn.itcast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo2 {
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            statement = connection.createStatement();

//            String sql = "insert into account values(NULL, 'xiao', 10000)";
//            int i = statement.executeUpdate(sql);
//            if(i > 0){
//                System.out.println("插入成功");
//            }else {
//                System.out.println("插入失败");
//            }

            String sql = "update account set balance = 100 where name = 'xiao'";
            int i = statement.executeUpdate(sql);
            if(i > 0){
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
