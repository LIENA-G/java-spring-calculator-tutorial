package com.calculator.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;


@Configuration
////is responsible for registering the necessary Spring components that power annotation-driven transaction management,
//// such as the TransactionInterceptor and the proxy- or AspectJ-based advice that weave the interceptor into the call
//// stack when the Repository's @Transactional methods are invoked.
@EnableTransactionManagement
// The @EnableJpaRepositories tells Spring that it must create repository instances.
@EnableJpaRepositories("com.calculator.repository")
//Spring @PropertySource annotations is mainly used to read from properties file using Spring’s Environment interface.
//if you used @Value you should configure static PropertySourcesPlaceholderConfigurer.
@PropertySource(value = {"classpath:Application.properties"})

public class JpaConfig {
//  Environment interface provides getter methods to read the individual property values.
    @Autowired
    private Environment environment;

//Provides a utility class for easy DataSource access, a PlatformTransactionManager for a single DataSource,
// and various simple DataSource implementations.
//HikariDataSource is a very fast data source.
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return new HikariDataSource(dataSource);
    }

    // SPI interface that allows to plug in vendor-specific behavior into Spring's EntityManagerFactory creators
//  Serves as single configuration point for all vendor-specific properties.
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }


//A connection to a database is represented by an EntityManager instance, which also provides functionality for
// performing operations on a database. Many applications require multiple database connections during their lifetime.
// The main role of an EntityManagerFactory instance is to support instantiation of EntityManager instances.
// An EntityManagerFactory is constructed for a specific database, and by managing resources efficiently
// (e.g. a pool of sockets), it provides an efficient way to construct multiple EntityManager instances for that database.
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        //Configure the Hibernate specific implementation of the JpaVendorAdapter interface. It will initialize our
// configuration with the default settings that are compatible with Hibernate.
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//Configure the JPA properties that are used to provide additional configuration to the used JPA provider.
        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com/calculator");
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean.getObject();
    }

// PlatformTransactionManager provides an abstraction over the different transaction APIs,like JPA transactions,
// JDBC transactions,Hibernate transactions ant etc.
    @Bean
    public PlatformTransactionManager transactionManager() {
//Because we are using JPA, we have to create a transaction manager bean that integrates the JPA provider with the
// Spring transaction mechanism. We can do this by using the JpaTransactionManager class as the transaction manager
// of our application.
        JpaTransactionManager txManager = new JpaTransactionManager();
//Configure the entity manager factory whose transactions are managed by the created JpaTransactionManager object.
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}
