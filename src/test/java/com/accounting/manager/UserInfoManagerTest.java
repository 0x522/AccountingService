package com.accounting.manager;

import com.accounting.converter.p2c.UserInfoP2CConverter;
import com.accounting.dao.UserInfoDao;
import com.accounting.exception.ResourceNotFoundException;
import com.accounting.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


class UserInfoManagerTest {
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDAO;


    @BeforeEach
    void setUp() {
        //对此类中的所有Mock注解都初始化使用
        MockitoAnnotations.initMocks(this);
        this.userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    void testGetUserInfoByUserId() {
        //Arrange
        val userId = 1L;
        val username = "hardcore";
        val password = "hardcore";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .createTime(createTime)
                .build();
        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);
        //Act
        val result = userInfoManager.getUserInfoByUserId(userId);

//        //Junit5 Assert
//        assertEquals(userId, result.getId());
//        assertEquals("hardcore", result.getUsername());
//        assertEquals("hardcore", result.getPassword());

        //AssertJ
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
        verify(userInfoDAO).getUserInfoById(eq(userId));
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidId() {
        //Arrange
        val userId = -1L;
        doReturn(null).when(userInfoDAO).getUserInfoById(userId);
        //Act&Assert
        assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserId(userId));
        verify(userInfoDAO).getUserInfoById(userId);
    }
}


