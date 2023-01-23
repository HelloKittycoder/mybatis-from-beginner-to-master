package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/12 12:38
 */
public interface RoleMapper {

    @Select({"select id, role_name roleName, enabled, " +
                "create_by `createInfo.createBy`, " +
                "create_time `createInfo.createTime` " +
                "from sys_role " +
            "where id = #{id}"})
    SysRole selectById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createInfo.createBy", column = "create_by"),
            @Result(property = "createInfo.createTime", column = "create_time")
    })
    @Select("select id, role_name roleName, enabled, create_by, create_time from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    /**
     * 新增角色-不需要返回主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time)",
            "values(#{id}, #{roleName}, #{enabled}, #{createInfo.createBy}, #{createInfo.createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    /**
     * 新增角色-返回自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createInfo.createBy}, #{createInfo.createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    /**
     * 新增角色-返回非自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createInfo.createBy}, #{createInfo.createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)
    int insert3(SysRole sysRole);

    /**
     * 根据主键更新
     * @param sysRole
     * @return
     */
    @Update({"update sys_role " +
            "set role_name = #{roleName}, " +
                "enabled = #{enabled}, " +
                "create_by = #{createInfo.createBy}, " +
                "create_time = #{createInfo.createTime, jdbcType=TIMESTAMP} " +
            "where id = #{id}"})
    int updateById(SysRole sysRole);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);

    /**
     * 查询角色及其对应权限
     * @return
     */
    List<SysRole> selectAllRoleAndPrivileges();

    /**
     * 根据用户ID获取用户的角色信息
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserIdChoose(Long userId);
}
