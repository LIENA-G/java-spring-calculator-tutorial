package com.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.calculator"})
//Adding this annotation to a @Configuration class imports the Spring MVC configuration from WebMvcConfigurationSupport
//which is the main class providing the configuration behind the MVC Java config.
//note:@EnableWebMvc and <mvc:annotation-driven /> have the same purpose.
@EnableWebMvc
@PropertySource(value = {"classpath:application.properties"})
//To customize the configuration imported by @EnableWebMvc, we should extend the class WebMvcConfigurerAdapter
// and override the methods we want to do related customization with. Our extended WebMvcConfigurerAdapter methods
// are called back from WebMvcConfigurationSupport during configuration stage. Note that WebMvcConfigurerAdapter has
// been deprecated since 5.0, so there we should implement WebMvcConfigurer alternatively.
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String VIEWS = "/WEB-INF/jsp/";
//you can use PropertySourcesPlaceholderConfigurer whenever you want to externalize values from external property file
// using @Value.
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
//Resolves view names to views.
   @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp(VIEWS, ".jsp");}

//We register the Spring DispatcherServlet to the / path. This replaces the DefaultServlet; therefore we have to
// register a default servlet handler in the configuration file.
// The configureDefaultServletHandling() configures a DefaultServletHttpRequestHandler with a URL mapping of /** and
// the lowest priority relative to other URL mappings. This way the static resource requests are handled by the
// container's default Servlet.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
