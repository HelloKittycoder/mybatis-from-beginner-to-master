package tk.mybatis.simple.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Enabled 类型处理器
 * @author shucheng
 * @date 2023/1/26 16:36
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {
    private final Map<Integer, Enabled> enabledMap = new HashMap<Integer, Enabled>();

    public EnabledTypeHandler() {
        for (Enabled enabled : Enabled.values()) {
            enabledMap.put(enabled.getValue(), enabled);
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Enabled parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public Enabled getResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return enabledMap.get(value);
    }
}
