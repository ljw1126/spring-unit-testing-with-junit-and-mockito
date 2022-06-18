package com.in28minutes.unittesting.unittesting.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * application 실행하지 않고 해당 컨트롤러 테스트 가능
 * 아래 두 어노테이션의 의미는
 * '이것은 HelloWorldContorller를 실행시키는 spring test 다' 라는 의미
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloWorld_basic() throws Exception {
        // call "/hello-world" url GET application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();

        // verify "Hello World"
        assertEquals("Hello World", mvcResult.getResponse().getContentAsString()); // toString() 호출하니 주소값 가져와서 fail
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON.toString(), mvcResult.getResponse().getContentType());
    }

    @Test
    public void helloWorld_improve_UsingMockMvcResultMatchers() throws Exception {
        // call "/hello-world" url GET application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();

     }
}
