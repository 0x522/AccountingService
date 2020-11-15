package com.accounting.manager;

import com.accounting.converter.p2c.UserInfoP2CConverter;
import com.accounting.dao.UserInfoDao;
import com.accounting.exception.ResourceNotFoundException;
import com.accounting.model.common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserInfoManagerImpl implements UserInfoManager {
    private final UserInfoDao userInfoDao;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDao userInfoDao,
                               final UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return Optional
            .ofNullable(userInfoDao.getUserInfoById(userId))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("User %s was not found", userId)));
    }
}
