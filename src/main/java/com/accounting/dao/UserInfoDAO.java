package com.accounting.dao;

import com.accounting.model.persistence.UserInfo;

public interface UserInfoDAO {
    UserInfo getUserInfoById(Long id);

    void createNewUser(String username, String password);
}
