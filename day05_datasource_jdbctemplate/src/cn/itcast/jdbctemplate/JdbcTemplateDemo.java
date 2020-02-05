package cn.itcast.jdbctemplate;

import cn.itcast.JdbcUtil;
import cn.itcast.bean.User;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    private static JdbcTemplate jdbcTemplate = JdbcUtil.createJdbcTemplate();

//    public static void main(String[] args) {
//        testInsert();
//    }

    /**
     * jdbcTemplate插入操作测试
     */
    @Test
    public void testInsert() {
        String sql = "insert into user values(NULL, ?, ?)";
        int ret = jdbcTemplate.update(sql, "tencent1", "123");
        if (ret > 0) {
            System.out.println("insert succ");
        } else {
            System.out.println("insert fail");
        }
    }

    /**
     * 测试queryForList方法
     */
    @Test
    public void testQueryForList() {
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
    }

    @Test
    public void testQueryForMap() {
        String sql = "select * from user where name = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, "tencent");
        System.out.println(map);
    }

    @Test
    public void testQuery() {
        String sql = "select * from user";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(query);
    }

    @Test
    public void testQueryForObject(){
        String sql = "select count(id) from user";
        Long aLong = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }
}
