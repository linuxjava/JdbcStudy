package cn.itcast;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo {
    public static void main(String[] args){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            Connection connection = ds.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
