package com.calculator.config;
//DispatcherServlet can be configured programmatically by implementing or extending either of these three support
// classes provided by Spring:
//1/WebAppInitializer interface
//2/AbstractDispatcherServletInitializer abstract class
//3/AbstractAnnotationConfigDispatcherServletInitializer abstract class
//by using the last one web application context +root application context will be created automatically without many
// lines of code.
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Order(2)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//root application context
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { com.calculator.config.AppConfig.class, com.calculator.config.JpaConfig.class};
    }
//web application context
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebMvcConfig.class };
    }

//we configure the Spring dispatcher servlet to the / path, which rewrites the default servlet's one. We enable
// the default servlet with DefaultServletHandlerConfigurer.
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
