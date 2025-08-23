package com.lilac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilac.entity.Admin;
import com.lilac.entity.DTO.AdminPageDTO;
import com.lilac.entity.Result;
import com.lilac.entity.VO.PageVO;
import com.lilac.mapper.AdminMapper;
import com.lilac.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 分页查询管理员列表
     */
    @Override
    public Result Page(AdminPageDTO adminPageDTO) {
        PageHelper.startPage(adminPageDTO.getPageNum(), adminPageDTO.getPageSize());
        List<Admin> admins = adminMapper.pageList(adminPageDTO.getUserName(), adminPageDTO.getPhone(),adminPageDTO.getEmail());
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        PageVO page = new PageVO(pageInfo.getList(), pageInfo.getTotal());
        return Result.success(page);
    }

    /**
     * 新增管理员
     */
    @Override
    public void save(Admin admin) {
        adminMapper.save(admin);
    }

    /**
     * 更新管理员
     */
    @Override
    public void update(Admin admin) {
        adminMapper.update(admin);
    }

    /**
     * 删除管理员
     */
    @Override
    public void delete(Integer id) {
        adminMapper.delete(id);
    }

}
