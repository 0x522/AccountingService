package com.accounting.manager;

import com.accounting.converter.p2c.UserInfoP2CConverter;
import com.accounting.dao.UserInfoDAO;
import com.accounting.model.common.UserInfo;
import lombok.val;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoManagerImpl implements UserInfoManager {
    private final UserInfoDAO userInfoDAO;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDAO userInfoDAO, final UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDAO = userInfoDAO;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public @Nullable UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = userInfoDAO.getUserInfoById(userId);
        return userInfoP2CConverter.convert(userInfo);
    }
}
