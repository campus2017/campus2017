package com.example.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

/**
 * Created by WangWeiyi on 2017/6/29 0029.
 */
@Configuration
@EnableWebMvc

public class JspConfig extends WebMvcConfigurerAdapter {
    //配置视图解析器、静态资源转发器、web组件的bean等
    //继承WebMvcConfigurerAdapter可以使用其静态资源处理方法
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver () {
        InternalResourceViewResolver viewResolver = new   InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //用于上传文件的解析器
@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        resolver.setResolveLazily(true);// resolveLazily属性启用是为了推迟文件解析
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(2 * 1024 * 1024);// 上传文件大小 2M 50*1024*1024
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }


    //将静态资源的请求转发到Servlet容器处理，而不是DispatcherServlet处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
