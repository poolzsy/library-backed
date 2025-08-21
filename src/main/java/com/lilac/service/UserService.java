package com.lilac.service;

import com.lilac.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    List<User> listUsers();
}
