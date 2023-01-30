package tk.mybatis.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shucheng
 * @date 2023/1/30 9:19
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
}
