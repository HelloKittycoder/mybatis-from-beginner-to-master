package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/14 21:52
 */
public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById(1L);
            // role 不为空
            Assert.assertNotNull(role);
            // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById2(1L);
            // role 不为空
            Assert.assertNotNull(role);
            // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectAll 方法，查询所有角色
            List<SysRole> roleList = roleMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0个
            Assert.assertTrue(roleList.size() > 0);
            // 验证下划线字段是否映射成功
            Assert.assertNotNull( roleList.get(0).getRoleName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            // 调用 insert 方法，新增角色
            SysRole role = new SysRole();
            role.setRoleName("测试用户");
            role.setEnabled(1);
            role.setCreateBy("1");
            role.setCreateTime(new Date());
            int result = roleMapper.insert(role);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // id为null，没有给id赋值，并且没有配置回写id的值
            Assert.assertNull(role.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            // 调用 insert 方法，新增角色
            SysRole role = new SysRole();
            role.setRoleName("测试用户");
            role.setEnabled(1);
            role.setCreateBy("1");
            role.setCreateTime(new Date());
            int result = roleMapper.insert2(role);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // 因为id回写，所以id不为null
            Assert.assertNotNull(role.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            // 调用 insert 方法，新增角色
            SysRole role = new SysRole();
            role.setRoleName("测试用户");
            role.setEnabled(1);
            role.setCreateBy("1");
            role.setCreateTime(new Date());
            int result = roleMapper.insert3(role);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // 因为id回写，所以id不为null
            Assert.assertNotNull(role.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1L);
            // 当前roleName为管理员
            Assert.assertEquals("管理员", role.getRoleName());
            // 修改角色名
            role.setRoleName("管理员_test");
            // 更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = roleMapper.updateById(role);
            // 只更新1条数据
            Assert.assertEquals(1,  result);
            // 修改后的角色名是管理员_test
            Assert.assertEquals("管理员_test", role.getRoleName());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1L);
            // 现在还能查询出role对象
            Assert.assertNotNull(role);
            // 调用方法删除
            Assert.assertEquals(1, roleMapper.deleteById(1L));
            // 再次查询，这时应该没有值，为null
            Assert.assertNull(roleMapper.selectById(1L));
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }
}
