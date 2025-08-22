package com.lilac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilac.entity.DTO.UserPageDTO;
import com.lilac.entity.Result;
import com.lilac.entity.User;
import com.lilac.entity.VO.PageVO;
import com.lilac.enums.HttpsCodeEnum;
import com.lilac.exception.SystemException;
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
    public List<User> list() {
        return userMapper.list();
    }

    /**
     * 分页获取用户
     *
     * @param userPageDTO
     * @return
     */
    @Override
    public Result Page(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPageNum(), userPageDTO.getPageSize());
        List<User> users = userMapper.pageList(userPageDTO.getName(), userPageDTO.getPhone());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageVO page = new PageVO(pageInfo.getList(), pageInfo.getTotal());
        return Result.success(page);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public void save(User user) {
        if (userMapper.selectByUserName(user.getUsername()) != null) {
            throw new SystemException(HttpsCodeEnum.USER_NAME_EXIST);
        }
        if (userMapper.selectByPhone(user.getPhone()) != null) {
            throw new SystemException(HttpsCodeEnum.USER_PHONE_EXIST);
        }
        userMapper.save(user);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void update(User user) {
        if (userMapper.selectByUserName(user.getUsername()) != null) {
            throw new SystemException(HttpsCodeEnum.USER_NAME_EXIST);
        }
        if (userMapper.selectByPhone(user.getPhone()) != null) {
            throw new SystemException(HttpsCodeEnum.USER_PHONE_EXIST);
        }
        userMapper.update(user);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
