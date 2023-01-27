package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

/**
 * @author shucheng
 * @date 2023/1/27 13:58
 */
public class CacheTest extends BaseMapperTest {

    @Test
    public void testL1Cache() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        SysUser user1;
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectById 方法，查询 id = 1 的用户
            user1 = userMapper.selectById(1L);
            // 对当前获取的对象重新赋值
            user1.setUserName("New Name");
            // 再次查询获取 id 相同的用户
            SysUser user2 = userMapper.selectById(1L);
            // 虽然没有更新数据库，但是这个用户名和 user1 重新赋值的名字相同
            Assert.assertEquals("New Name", user2.getUserName());
            // 无论如何，user2 和 user1 完全就是同一个实例
            Assert.assertEquals(user1, user2);
        } finally {
            // 关闭当前的 sqlSession
            sqlSession.close();
        }

        System.out.println("开启新的 sqlSession");
        // 开始另一个新的 session
        sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectById 方法，查询 id = 1 的用户
            SysUser user2 = userMapper.selectById(1L);
            // 第二个 session 获取的用户名仍然是 admin
            Assert.assertNotEquals("New Name", user2.getUserName());
            // 这里的 user2 和前一个 session 查询的结果是两个不同的实例
            Assert.assertNotEquals(user1, user2);
            // 执行删除操作
            userMapper.deleteById(2L);
            // 获取 user3
            SysUser user3 = userMapper.selectById(1L);
            // 这里的 user2 和 user3 是两个不同的实例
            Assert.assertNotEquals(user2, user3);
        } finally {
            // 关闭 sqlSession
            sqlSession.close();
        }
    }
}
