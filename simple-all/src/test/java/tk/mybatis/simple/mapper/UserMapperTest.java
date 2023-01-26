package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.*;

import java.util.*;

/**
 * @author shucheng
 * @date 2023/1/12 14:53
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectById 方法，查询 id = 1 的用户
            SysUser user = userMapper.selectById(1L);
            // user 不为空
            Assert.assertNotNull(user);
            // userName = admin
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectAll 方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(userList);
            // 用户数量大于0个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0个
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRoleExtend> roleList = userMapper.selectRolesByUserId2(1L);
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0个
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId3() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole2> roleList = userMapper.selectRolesByUserId3(1L);
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0个
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            // 正常情况下应该读入一张图片存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中，特别注意这里的返回值result是执行的SQL影响的行数
            int result = userMapper.insert(user);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // id为null，没有给id赋值，并且没有配置回写id的值
            Assert.assertNull(user.getId());
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
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert2(user);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // 因为id回写，所以id不为null
            Assert.assertNotNull(user.getId());
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert3(user);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // 因为id回写，所以id不为null
            Assert.assertNotNull(user.getId());
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库查询一个user对象
            SysUser user = userMapper.selectById(1L);
            // 当前userName为admin
            Assert.assertEquals("admin", user.getUserName());
            // 修改用户名
            user.setUserName("admin_test");
            // 修改邮箱
            user.setUserEmail("admin_test@mybatis.tk");
            // 更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = userMapper.updateById(user);
            // 只更新1条数据
            Assert.assertEquals(1, result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            // 修改后的名字是admin_test
            Assert.assertEquals("admin_test", user.getUserName());
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
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库查询一个user对象
            SysUser user = userMapper.selectById(1L);
            // 现在还能查询出user对象
            Assert.assertNotNull(user);
            // 调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(1L));
            // 再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1L));

            /*// 写法2：
            // 从数据库查询一个user对象
            SysUser user = userMapper.selectById(1L);
            // 现在还能查询出user对象
            Assert.assertNotNull(user);
            // 调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(user));
            // 再次查询，这时应该没有值，为nll
            Assert.assertNull(userMapper.selectById(1L));*/
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
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);

            /*// 写法2：使用Map传参
            Map<String, Object> params = new HashMap<>();
            params.put("userId", 1L);
            params.put("enabled", 1);
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(params);*/

            /*// 写法3：使用多个对象传参
            SysUser user = new SysUser();
            user.setId(1L);
            SysRole role = new SysRole();
            role.setEnabled(1);
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled2(user, role);*/

            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0个
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            // 只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);

            // 只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);

            // 同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            // 由于没有同时符合这两个条件的用户，因此查询结果数为0
            Assert.assertTrue(userList.size() == 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个新的user对象
            SysUser user = new SysUser();
            // 更新 id = 1 的用户
            user.setId(1L);
            // 修改邮箱
            user.setUserEmail("test@mybatis.tk");
            // 更新邮箱，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = userMapper.updateByIdSelective(user);
            // 只更新1条数据
            Assert.assertEquals(1, result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            // 修改后的名字保持不变，但是邮箱变成了新的
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2Selective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个新的user对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            // user.setUserEmail("test-selective@mybatis.tk");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            // 插入数据库
            userMapper.insert2(user);
            // 获取插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            /**
             * 这里假设id和userName都是唯一的
             */
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 同时传id和userName时（此时when先匹配到id，不会再进行后续匹配了）
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 只传userName时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 当id和userName都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            // 业务逻辑中必须校验idList.size()>0
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2, userList.size());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<SysUser>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            // 将新建的对象批量插入数据库中
            // 特别注意这里的返回值result是执行的SQL影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);

            /**
             * 从MyBatis 3.3.1版本开始，MyBatis开始支持批量新增回写主键值的功能，只要添加
             * useGeneratedKeys="true" keyProperty="id"这两个属性即可
             */
            /*for (SysUser user : userList) {
                System.out.println(user.getId());
            }*/
        } finally {
            // 为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询条件，同样也是更新字段，必须保证该值存在
            map.put("id", 1L);
            // 要更新的其他字段
            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "12345678");
            // 更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = userMapper.updateByMap(map);
            // 只更新1条数据
            Assert.assertEquals(1, result);
            // 根据当前id查询修改后的数据
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 特别注意，在测试数据中，id=1L的用户有两个角色，不适合这个例子
            // 这里使用只有一个角色的用户（id=1001L）
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            // user 不为空
            Assert.assertNotNull(user);
            // user.role 也不为空
            Assert.assertNotNull(user.getRole());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 这里使用只有一个角色的用户（id = 1001L）
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            // user 不为空
            Assert.assertNotNull(user);
            System.out.println("调用 user.equals(null)");
            user.equals(null);
            // user.role 也不为空
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getRole());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数：" + userList.size());
            for (SysUser user : userList) {
                System.out.println("用户名：" + user.getUserName());
                for (SysRole role : user.getRoleList()) {
                    System.out.println("角色名：" + role.getRoleName());
                    for (SysPrivilege privilege : role.getPrivilegeList()) {
                        System.out.println("权限名：" + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRolesSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("用户名：" + user.getUserName());
            for (SysRole role : user.getRoleList()) {
                System.out.println("角色名：" + role.getRoleName());
                for (SysPrivilege privilege : role.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("用户名：" + user.getUserName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserPage() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> params = new HashMap<>();
            params.put("userName", "ad");
            params.put("offset", 0);
            params.put("limit", 10);
            List<SysUser> userList = userMapper.selectUserPage(params);
            Long total = (Long) params.get("total");
            System.out.println("总数：" + total);
            for (SysUser user : userList) {
                System.out.println("用户名：" + user.getUserName());
            }
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsertAndDelete() {
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            // 插入用户信息和角色关联信息
            userMapper.insertUserAndRoles(user, "1,2");
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getCreateTime());
            // 可以执行下面的commit后再查看数据库中的数据
            // sqlSession.commit();
            // 测试删除刚刚删除的数据
            userMapper.deleteUserById(user.getId());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }
}
