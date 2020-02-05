package cn.itcast;

import cn.itcast.util.JdbcUtil;

import java.sql.*;

public class JdbcDemo4 {
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();

            String sql = "select * from account";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String balance = resultSet.getString("balance");
                System.out.println(id + ":" + name + ":" + balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet, statement, connection);
        }
    }
}
