package com.accounting.converter.p2c;


import com.accounting.model.persistence.UserInfo;
import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoP2CConverter
    extends
    Converter<com.accounting.model.persistence.UserInfo, com.accounting.model.common.UserInfo> {

    @Override
    protected com.accounting.model.common.UserInfo doForward(@NotNull UserInfo userInfo) {
        return com.accounting.model.common.UserInfo.builder()
            .id(userInfo.getId())
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .build();
    }

    @Override
    protected UserInfo doBackward(@NotNull com.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
            .id(userInfo.getId())
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .build();
    }
}
