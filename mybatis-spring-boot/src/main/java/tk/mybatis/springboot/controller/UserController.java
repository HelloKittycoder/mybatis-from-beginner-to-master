package tk.mybatis.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.springboot.service.UserService;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/30 20:14
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public SysUser user(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @RequestMapping
    public List<SysUser> users() {
        return userService.findAll();
    }

    @RequestMapping("/selectById")
    public SysUser selectById(@RequestParam Long id) {
        return userService.findById(id);
    }
}
