package tk.mybatis.simple.model;

/**
 * @author shucheng
 * @date 2023/1/12 21:59
 */
public class SysRoleExtend extends SysRole {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
