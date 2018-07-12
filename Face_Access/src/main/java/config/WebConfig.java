package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.logging.Logger;

@Configuration
@EnableWebMvc
//@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"controller", "filter"})
public class WebConfig implements WebMvcConfigurer {

    private Logger logger  = Logger.getLogger("WebConfig");

    /**
     * 配置视图解析器，
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    @Bean
//    public CustomExceptionResolver exceptionResolver() {
//        logger.info("-----CustomExceptionResolver Create-----");
//        return new CustomExceptionResolver();
//    }

    /**
     * 配置静态资源处理，
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
