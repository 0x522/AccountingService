package com.accounting.manager;

import com.accounting.model.common.UserInfo;

public interface UserInfoManager {
    UserInfo getUserInfoByUserId(Long userId);
}
