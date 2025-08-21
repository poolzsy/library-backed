package com.lilac.service.impl;

import com.lilac.entity.User;
import com.lilac.mapper.UserMapper;
import com.lilac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有用户
     *
     * @return
     */
    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }
}
