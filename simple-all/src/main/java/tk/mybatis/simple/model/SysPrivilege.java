package tk.mybatis.simple.model;

/**
 * 权限表
 * @author shucheng
 * @date 2023/1/11 23:03
 */
public class SysPrivilege {
    /**
     * 权限ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String privilegeName;
    /**
     * 权限URL
     */
    private String privilegeUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }
}
