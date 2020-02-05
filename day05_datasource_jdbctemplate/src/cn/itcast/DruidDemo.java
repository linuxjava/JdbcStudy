package cn.itcast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
//        Properties properties = new Properties();
//        InputStream resourceAsStream = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
//        properties.load(resourceAsStream);
//
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);

        testDruidUtil();
    }

    public static void testDruidUtil() throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into user values(NULL, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "test1");
        preparedStatement.setString(2, "1234567");

        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("DruidUtil测试成功");
        }else {
            System.out.println("DruidUtil测试失败");
        }

        JdbcUtil.close(preparedStatement, connection);
    }
}
