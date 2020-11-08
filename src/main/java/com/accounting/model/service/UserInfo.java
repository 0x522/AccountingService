package com.accounting.model.service;

import lombok.Builder;
import lombok.Data;

/**
 * a class towards Controller.
 * 对应Controller层结构
 */
@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
