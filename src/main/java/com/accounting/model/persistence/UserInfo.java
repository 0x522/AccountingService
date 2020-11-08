package com.accounting.model.persistence;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * a class towards Persistence.
 * 对应数据库表结构.
 */
//不用写getter/setter
@Data
//省略builder模式
@Builder
//无参构造器自动实现
@NoArgsConstructor
//全参
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String password;
    private LocalDate createTime;
    private LocalDate updateTime;
}
