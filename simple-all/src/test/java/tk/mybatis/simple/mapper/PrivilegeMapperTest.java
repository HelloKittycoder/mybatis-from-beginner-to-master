package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

/**
 * @author shucheng
 * @date 2023/1/15 15:34
 */
public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 PrivilegeMapper 接口
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            // 调用 selectById 方法，查询 id = 1 的权限
            SysPrivilege privilege = privilegeMapper.selectById(1L);
            // privilege 不为空
            Assert.assertNotNull(privilege);
            // privilegeName = 用户管理
            Assert.assertEquals("用户管理", privilege.getPrivilegeName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }
}