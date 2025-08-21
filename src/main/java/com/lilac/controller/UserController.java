package com.lilac.controller;

import com.lilac.entity.Result;
import com.lilac.entity.User;
import com.lilac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result listUsers(){
        List<User> users = userService.listUsers();
        return Result.success(users);
    }
}
