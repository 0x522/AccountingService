package com.accounting.manager;

import com.accounting.model.common.UserInfo;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface UserInfoManager {
    @Nullable UserInfo getUserInfoByUserId(Long userId);
}
