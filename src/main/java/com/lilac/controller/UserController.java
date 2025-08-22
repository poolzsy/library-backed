package com.lilac.controller;

import com.lilac.entity.DTO.UserPageDTO;
import com.lilac.entity.Result;
import com.lilac.entity.User;
import com.lilac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    /**
     * 分页获取用户
     * @param userPageDTO
     * @return
     */
    @GetMapping("/page")
    public Result Page(UserPageDTO userPageDTO){
        return userService.Page(userPageDTO);
    }
}
