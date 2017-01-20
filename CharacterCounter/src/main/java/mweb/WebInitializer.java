package mweb;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class WebInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext)
      throws ServletException {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(MyMvcConfig.class);
    ctx.setServletContext(servletContext);

    Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
    servlet.setAsyncSupported(true);
  }
}

