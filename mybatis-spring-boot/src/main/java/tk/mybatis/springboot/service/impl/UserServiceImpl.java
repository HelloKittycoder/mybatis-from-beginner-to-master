package tk.mybatis.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.springboot.service.UserService;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/30 20:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<SysUser> findAll() {
        return userMapper.selectAll();
    }
}
