package com.zhenghan.qunar.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: 郑含
 * Date: 2016/12/27
 * Time: 20:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("/src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration("classpath:spring-context.xml")
})
public class CounterControllerTest {
    @Autowired
    private WebApplicationContext wap;
    private MockMvc moc;
    @Before
    public void before(){
        moc = MockMvcBuilders.webAppContextSetup(wap).build();
    }
    @Test
    public void textTest() throws Exception {
        moc.perform(get("/textcount").accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"stream\":\"郑含isveryhappy,啊啊啊啊。\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andDo(print());
    }
}
