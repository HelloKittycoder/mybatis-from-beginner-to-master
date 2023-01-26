package tk.mybatis.simple.type;

/**
 * @author shucheng
 * @date 2023/1/26 15:12
 */
public enum Enabled {
    disabled(0), // 禁用
    enabled(1); // 启用

    private final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
