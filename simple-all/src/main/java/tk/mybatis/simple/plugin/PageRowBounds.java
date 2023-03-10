package tk.mybatis.simple.plugin;

import org.apache.ibatis.session.RowBounds;

/**
 * 可以记录 total 的分页参数
 * @author shucheng
 * @date 2023/1/28 22:56
 */
public class PageRowBounds extends RowBounds {
    private long total;

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int offset, int limit) {
        super(offset, limit);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
