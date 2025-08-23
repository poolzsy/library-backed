package com.lilac.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    //会员号
    private String cardId;
    //年龄
    private Integer age;
    //性别
    private String gender;
    //是否是会员
    private String isMember;
    //手机号
    private String phone;
    //住址
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

