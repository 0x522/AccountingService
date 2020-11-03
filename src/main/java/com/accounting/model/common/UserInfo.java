package com.accounting.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对应Manager层结构
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
