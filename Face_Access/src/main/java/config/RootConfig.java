package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import util.VerifyCodeFactory;

import java.util.logging.Logger;

@Configuration
@ComponentScan(basePackages = {"dao", "service", "manager"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@Import(CachingConfig.class)
public class RootConfig {

    private Logger logger = Logger.getLogger("logger");

//    @Bean
//    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setPackagesToScan("entity");
//        Properties properties = new Properties();
//        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//        factoryBean.setHibernateProperties(properties);
//        logger.info("-----LocalSessionFactoryBean Init Success-----");
//        return factoryBean;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://139.224.239.13:3306/my_data_base?characterEncoding=UTF-8&useSSL=false");
//        ds.setUsername("root");
//        ds.setPassword("admin");
//        logger.info("-----DataSource Init Success-----");
//        return ds;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        logger.info("-----BCryptPasswordEncoder Create Success-----");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public VerifyCodeFactory imageCodeFactory() {
        return new VerifyCodeFactory();
    }

    //    @Bean(name = "captchaProducer")
//    public com.google.code.kaptcha.impl.DefaultKaptcha captchaProducer() {
//    }

    @Bean(name = "factory")
    public SessionFactory factory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return factory;
    }

}
