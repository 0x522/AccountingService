package com.accounting.controller;

import com.accounting.converter.c2s.UserInfoC2SConverter;
import com.accounting.manager.UserInfoManager;
import com.accounting.model.service.UserInfo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
@Slf4j
public class UserController {
    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(final UserInfoC2SConverter userInfoC2SConverter, final UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public @Nullable UserInfo getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.debug("Get user info by user id {}", userId);
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return userInfoC2SConverter.convert(userInfo);
    }
}
