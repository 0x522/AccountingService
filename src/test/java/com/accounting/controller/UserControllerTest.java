package com.accounting.controller;

import com.accounting.converter.c2s.UserInfoC2SConverter;
import com.accounting.exception.GlobalExceptionHandler;
import com.accounting.exception.ResourceNotFoundException;
import com.accounting.manager.UserInfoManager;
import com.accounting.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;
    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @AfterEach
        //方法注解，在每个测试方法之后运行使用该注解的方法
    void tearDown() {
        reset(userInfoManager);
        reset(userInfoC2SConverter);
    }

    @Test
    void testGetUserInfoByUserId() throws Exception {
        //Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";
        UserInfo userInfoInCommon = UserInfo.builder()
            .id(userId)
            .username(username)
            .password(password)
            .build();
        com.accounting.model.service.UserInfo userInfoInService = com.accounting.model.service.UserInfo.builder()
            .id(userId)
            .username(username)
            .password(password)
            .build();
        //Act
        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());
        doReturn(userInfoInService).when(userInfoC2SConverter).convert(userInfoInCommon);
        //Assert
        mockMvc.perform(get("/v1.0/users/" + userId))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));

        verify(userInfoManager).getUserInfoByUserId(anyLong());
        verify(userInfoC2SConverter).convert(userInfoInCommon);
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() throws Exception {
        //Arrange
        val userId = -100L;
        doThrow(new ResourceNotFoundException(String.format("User %s was not found", userId)))
            .when(userInfoManager)
            .getUserInfoByUserId(anyLong());
        //Act
        //Assert
        mockMvc.perform(get("/v1.0/users/" + userId))
            .andExpect(status().is4xxClientError())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(
                "{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"This user id -100 is invalid\",\"statuscode\":400}"));
        verify(userInfoManager, never()).getUserInfoByUserId(anyLong());
    }
}