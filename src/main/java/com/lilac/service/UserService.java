package com.lilac.service;

import com.lilac.entity.DTO.UserPageDTO;
import com.lilac.entity.Result;
import com.lilac.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户
     * @return
     */
    List<User> list();

    /**
     * 分页获取用户
     * @param userPageDTO
     * @return
     */
    Result Page(UserPageDTO userPageDTO);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);
}
