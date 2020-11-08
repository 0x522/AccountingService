package com.accounting.dao;

import com.accounting.dao.mapper.UserInfoMapper;
import com.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserInfoDaoTest {
    @Mock
    private UserInfoMapper userInfoMapper;

    @InjectMocks //将@Mock的实例注入到注解@InjectMocks对象中，这是一个实例对象
    private UserInfoDaoImpl userInfoDAO;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this); 注解ExtendWith(MockitoExtension.class)可以省略初始化Mock
//        userInfoDAO = new UserInfoDaoImpl(userInfoMapper);
        System.out.println("中文");
    }

    @Test
    void testUserInfoByUserId() {

        //Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        val createTime = LocalDate.now();
        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();
        doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);
        //Act
        val result = userInfoDAO.getUserInfoById(userId);
        //Assert
        assertEquals(userInfo, result);

        verify(userInfoMapper).getUserInfoByUserId(userId);
    }
}
