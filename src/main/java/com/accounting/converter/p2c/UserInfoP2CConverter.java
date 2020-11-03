package com.accounting.converter.p2c;

import com.accounting.model.persistence.UserInfo;
import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserInfoP2CConverter extends Converter<com.accounting.model.persistence.UserInfo, com.accounting.model.common.UserInfo> {

    @Override
    protected com.accounting.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.accounting.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
