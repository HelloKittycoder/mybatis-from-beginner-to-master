package tk.mybatis.springboot;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper 名字生成器
 * @author shucheng
 * @date 2023/1/30 12:33
 */
public class MapperNameGenerator implements BeanNameGenerator {

    private Map<String, Integer> nameMap = new HashMap<>();

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // 获取类的名字，如 CountryMapper
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        // 将类名转换为规范的变量名，如 countryMapper
        String beanName = Introspector.decapitalize(shortClassName);
        // 判断名字是否已经存在，如果存在，则在名字后面增加序号
        if (nameMap.containsKey(beanName)) {
            int index = nameMap.get(beanName) + 1;
            nameMap.put(beanName, index);
            // 增加序号
            beanName += index;
        } else {
            nameMap.put(beanName, 1);
        }
        return beanName;
    }
}
