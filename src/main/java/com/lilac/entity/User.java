package com.lilac.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2025-08-21 14:21:54
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    //姓名
    private String name;
    //用户名
    private String username;
    //年龄
    private Integer age;
    //性别
    private String gender;
    //手机号
    private String phone;
    //住址
    private String address;

}

