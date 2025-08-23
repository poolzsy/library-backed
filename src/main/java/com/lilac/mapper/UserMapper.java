package com.lilac.mapper;

import com.lilac.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     */
    @Select("select * from user")
    List<User> list();

    /**
     * 分页查询用户
     */
    List<User> pageList(@Param("name") String name, @Param("phone") String phone);

    /**
     * 新增用户
     */
    void save(User user);

    /**
     * 修改用户
     */
    void update(User user);

    /**
     * 删除用户
     */
    @Delete("delete from user where id=#{id}")
    void delete(Integer id);

    /**
     * 根据手机号查询用户是否存在
     */
    User selectByPhone(@Param("phone") String phone, @Param("excludeId") Integer excludeId);

    /**
     * 根据会员卡号查询用户是否存在
     */
    Integer existsByCardId(@Param("cardId") String cardId);
}
