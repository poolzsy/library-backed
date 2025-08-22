package com.lilac.mapper;

import com.lilac.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> list();

    /**
     * 分页查询用户
     * @return
     */
    List<User> pageList(@Param("name") String name, @Param("phone") String phone);
}
