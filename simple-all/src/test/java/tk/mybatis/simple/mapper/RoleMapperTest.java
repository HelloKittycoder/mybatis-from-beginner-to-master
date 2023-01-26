package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.CreateInfo;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

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
            role.setEnabled(Enabled.enabled);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy("1");
            createInfo.setCreateTime(new Date());
            role.setCreateInfo(createInfo);
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
            role.setEnabled(Enabled.enabled);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy("1");
            createInfo.setCreateTime(new Date());
            role.setCreateInfo(createInfo);
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
            role.setEnabled(Enabled.enabled);
            CreateInfo createInfo = new CreateInfo();
            createInfo.setCreateBy("1");
            createInfo.setCreateTime(new Date());
            role.setCreateInfo(createInfo);
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

    @Test
    public void testSelectAllRoleAndPrivileges() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            // 能获取到角色列表
            Assert.assertNotNull(roleList.size() > 0);
            // 角色下面有权限数据（“管理员”角色下能取到权限数据）
            Assert.assertTrue(roleList.get(0).getPrivilegeList().size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 由于数据库数据 enable 都是1，所以给其中一个角色的enable赋值为0
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            // 获取用户1的角色
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRole r : roleList) {
                System.out.println("角色名：" + r.getRoleName());
                if (r.getId().equals(1L)) {
                    // 第一个角色存在权限信息
                    Assert.assertNotNull(r.getPrivilegeList());
                } else if (r.getId().equals(2L)) {
                    // 第二个角色的权限为null
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }

                for (SysPrivilege privilege : r.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    // 6.3.1 使用MyBatis提供的枚举处理器
    @Test
    public void testEnumUpdateById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 先查询出角色
            SysRole role = roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled, role.getEnabled());
            // 然后修改角色的 enabled 值为 disabled
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            // 验证修改是否成功
            Assert.assertEquals(Enabled.disabled, roleMapper.selectById(2L).getEnabled());
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }
}
