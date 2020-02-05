package cn.itcast.util;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo5 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String pwd = scanner.nextLine();

        boolean succ = login1(username, pwd);
        if(succ){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

    private static boolean login(String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();

            String sql = "select * from user WHERE name = '" + username + "' AND password = '" + password + "'";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet, statement, connection);
        }

        return false;
    }

    //ab' or 'a' = 'a
    private static boolean login1(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from user WHERE name = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet, statement, connection);
        }

        return false;
    }
}
