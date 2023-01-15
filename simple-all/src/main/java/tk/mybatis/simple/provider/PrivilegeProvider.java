package tk.mybatis.simple.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author shucheng
 * @date 2023/1/15 15:37
 */
public class PrivilegeProvider {

    public String selectById(final Long id) {
        return new SQL() {
            {
                SELECT("id, privilege_name, privilege_url");
                FROM("sys_privilege");
                WHERE("id = #{id}");
            }
        }.toString();
        /*// 写法2：手动拼接sql
        return "select id, privilege_name, privilege_url " +
                "from sys_privilege where id = #{id}";*/
    }
}
