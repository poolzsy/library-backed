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
     * @return
     */
    @Select("select * from user")
    List<User> list();

    /**
     * 分页查询用户
     * @return
     */
    List<User> pageList(@Param("name") String name, @Param("phone") String phone);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void delete(Integer id);

    /**
     * 根据用户名、手机号查询用户
     * @param user
     * @return
     */
    User selectByUniqueFields(User user);
}
