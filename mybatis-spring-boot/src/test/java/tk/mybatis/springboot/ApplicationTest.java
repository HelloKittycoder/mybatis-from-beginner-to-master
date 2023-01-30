package tk.mybatis.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.springboot.mapper.CountryMapper;

/**
 * @author shucheng
 * @date 2023/1/30 12:14
 */
@SpringBootApplication
public class ApplicationTest implements CommandLineRunner {

    @Autowired
    private CountryMapper countryMapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        countryMapper.selectAll();
    }
}