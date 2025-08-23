package com.lilac.service;

import com.lilac.entity.Admin;
import com.lilac.entity.DTO.AdminPageDTO;
import com.lilac.entity.Result;

public interface AdminService{

    /**
     * 分页查询管理员列表
     * @param adminPageDTO 查询参数
     * @return 管理员列表
     */
    Result Page(AdminPageDTO adminPageDTO);

    /**
     * 新增管理员
     * @param admin 管理员
     */
    void save(Admin admin);

    /**
     * 更新管理员
     * @param admin 管理员
     */
    void update(Admin admin);

    /**
     * 删除管理员
     * @param id 管理员id
     */
    void delete(Integer id);
}
