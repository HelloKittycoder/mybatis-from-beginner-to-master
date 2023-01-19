package tk.mybatis.generator;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/17 12:15
 */
public class GeneratorCountry {

    /**
     * 如果要新弄一个项目，如：simple
     * 就把 src/main/java 替换成 simple/src/main/java
     * src/main/resources 替换成 simple/src/main/resources
     * 然后把下面的test单元测试，改成用main方法写
     *
     * 另外需要在 mybatis-from-beginner-to-master 的 simple 项目下，建
     * src/main/java 和 src/main/resources 文件夹
     */
    @Test
    public void test() throws Exception {
        // MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        // 读取 MBG 配置文件
        InputStream is = Generator.class.getResourceAsStream(
                "/generator/generatorConfig-country.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        // 创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
