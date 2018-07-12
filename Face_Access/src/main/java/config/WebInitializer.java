package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Servlet 容器配置类
 * 使用 AbstractAnnotationConfigDispatcherServletInitializer 是 XML 的替代方案
 * 扩展 AbstractAnnotationConfigDispatcherServletInitializer 的任意类
 * 都会自动的配置 DispatcherServlet 和 Spring ApplicationContext，
 * Spring ApplicationContext 通常位于 Servlet Context 中
 **/
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定 ContextLoaderListener 配置类
     * ContextLoaderListener 加载应用中的其他 Bean ,
     * 这些 Bean 通常用于驱动应用后端的中间层和数据库组件
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 指定 DispatcherServlet 配置类
     * DispatcherServlet 启动时，自动创建 Spring ApplicationContext，
     *（ApplicationContext 对象代表 Spring 控制反转容器）
     * 并加载配置类或文件中声明的 Bean
     * DispatcherServlet 加载包含 WEB 组件的 Bean，如控制器、视图解析器以及处理器映射等
     **/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("D:\\upload", 2097152, 4194304, 0));
    }
}
