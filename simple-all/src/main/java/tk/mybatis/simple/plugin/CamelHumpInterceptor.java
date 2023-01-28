package tk.mybatis.simple.plugin;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * MyBatis Map 类型下划线 key 转小写驼峰形式
 * @author shucheng
 * @date 2023/1/28 20:45
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class})
)
public class CamelHumpInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 先执行得到结果，再对结果进行处理
        List<Object> list = (List<Object>) invocation.proceed();
        for (Object object : list) {
            // 如果结果是 Map 类型，就对 Map 的 key 进行转换
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * 处理 Map 类型
     * @param map
     */
    private void processMap(Map<String, Object> map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for (String key : keySet) {
            // 通过首字母是否为大写或是否包含下划线来判断 是否要处理为驼峰
            // 此处只通过这两个简单的标识来判断是否进行转换
            if ((key.charAt(0) >= 'A' && key.charAt(0) <= 'Z')
                    || key.indexOf('_') >= 0) {
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelHump(key), value);
            }
        }
    }

    /**
     * 将下划线风格替换为驼峰风格
     * @param inputString
     * @return
     */
    private String underlineToCamelHump(String inputString) {
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (c == '_') {
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
