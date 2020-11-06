package com.accounting.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
        //方法注解，在每个测试方法之前运行使用该注解的方法
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(HelloController.class).build();
    }

    @Test
    void testSayHello() throws Exception {
        //Arrange
        //Act
        //Assert
        mockMvc.perform(get("/v1.0/hello/v1.0/greeting").param("name", "World"))
                .andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"name\":\"Hello,World\"}"));
    }

}
