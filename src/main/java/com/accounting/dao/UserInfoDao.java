package com.accounting.dao;

import com.accounting.model.persistence.UserInfo;

public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);
}
