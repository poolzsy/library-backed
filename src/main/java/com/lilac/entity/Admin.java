package com.lilac.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (Admin)表实体类
 *
 * @author makejava
 * @since 2025-08-23 16:31:01
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private Integer id;
    //用户名
    private String userName;
    //密码
    private String password;
    //联系方式
    private String phone;
    //邮箱
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime updateTime;

}

