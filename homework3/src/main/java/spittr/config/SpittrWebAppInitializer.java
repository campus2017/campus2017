package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author zhaojun
 * @date 2016年12月15日
 * @reviewer
 * @see
 */
public class SpittrWebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected String[] getServletMappings(){
        return new String[]{"/"};
    }
    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class<?>[]{RootConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class<?>[]{WebConfig.class};

    }
    @Override
    protected void customizeRegistration(Dynamic registration){
        registration.setMultipartConfig(new MultipartConfigElement("D:/upload/temp/",2097152,4194304,0));
    }
}
