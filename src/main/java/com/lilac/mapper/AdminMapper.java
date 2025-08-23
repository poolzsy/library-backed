package com.lilac.mapper;

import com.lilac.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper{

    /**
     * 分页查询用户
     */
    List<Admin> pageList(@Param("userName") String userName, @Param("phone") String phone, @Param("email") String email);

    /**
     * 根据用户名查询用户
     */
    void save(Admin admin);

    /**
     * 根据用户名查询用户
     */
    void update(Admin admin);

    /**
     * 根据用户名查询用户
     */
    @Delete("delete from admin where id=#{id}")
    void delete(Integer id);
}
