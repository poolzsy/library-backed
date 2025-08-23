package com.lilac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilac.entity.DTO.UserPageDTO;
import com.lilac.entity.Result;
import com.lilac.entity.User;
import com.lilac.entity.VO.PageVO;
import com.lilac.enums.HttpsCodeEnum;
import com.lilac.exception.SystemException;
import com.lilac.mapper.UserMapper;
import com.lilac.service.UserService;
import com.lilac.utils.CardIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final int MAX_CARD_ID_RETRIES = 10; // 定义最大重试次数，防止死循环

    /**
     * 获取所有用户
     */
    @Override
    public List<User> list() {
        return userMapper.list();
    }

    /**
     * 分页获取用户
     */
    @Override
    public Result Page(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPageNum(), userPageDTO.getPageSize());
        List<User> users = userMapper.pageList(userPageDTO.getName(), userPageDTO.getPhone());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageVO page = new PageVO(pageInfo.getList(), pageInfo.getTotal());
        return Result.success(page);
    }

    /**
     * 新增用户
     */
    @Override
    public void save(User user) {
        validatePhoneUniqueness(user.getPhone(), null);
        handleMemberCard(user);
        userMapper.save(user);
    }

    /**
     * 更新用户
     */
    @Override
    public void update(User user) {
        validatePhoneUniqueness(user.getPhone(), user.getId());
        handleMemberCard(user);
        userMapper.update(user);
    }

    /**
     * 删除用户
     */
    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    /**
     * 统一处理会员卡号的生成与作废逻辑
     */
    private void handleMemberCard(User user) {
        boolean isMember = "1".equals(user.getIsMember());
        if (isMember) {
            // 如果是会员，且当前没有卡号，则为其生成一个唯一的卡号
            if (!StringUtils.hasText(user.getCardId())) {
                user.setCardId(generateUniqueCardId());
            }
        } else {
            // 如果不是会员，则强制将卡号设为 null
            user.setCardId(null);
        }
    }

    /**
     * 生成一个保证在数据库中唯一的会员卡号
     * @return 唯一的会员卡号
     */
    private String generateUniqueCardId() {
        for (int i = 0; i < MAX_CARD_ID_RETRIES; i++) {
            String cardId = CardIdGenerator.generate();
            if (userMapper.existsByCardId(cardId) == null) {
                return cardId;
            }
        }
        throw new SystemException(HttpsCodeEnum.USER_CARD_ID_ERROR);
    }

    /**
     * 专门用于校验手机号的唯一性
     * @param phone 手机号
     * @param excludeId 需要从校验中排除的用户ID (更新时传入)
     */
    private void validatePhoneUniqueness(String phone, Integer excludeId) {
        if (!StringUtils.hasText(phone)) {
            return;
        }
        User existingUser = userMapper.selectByPhone(phone, excludeId);
        if (existingUser != null) {
            throw new SystemException(HttpsCodeEnum.USER_PHONE_EXIST);
        }
    }
}
