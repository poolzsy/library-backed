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

    /**
     * 保存用户
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.delete(id);
        return Result.success();
    }
}
