package tk.mybatis.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 * @author shucheng
 * @date 2023/1/30 9:16
 */
@SpringBootApplication
@MapperScan(value = {
        "tk.mybatis.springboot.mapper",
        "tk.mybatis.simple.mapper"
    },
    nameGenerator = MapperNameGenerator.class
)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
