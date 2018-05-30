package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 使用 AbstractAnnotationConfigDispatcherServletInitializer 是 XML 的替代方案
 * 扩展 AbstractAnnotationConfigDispatcherServletInitializer 的任意类
 * 都会自动的配置 DispatcherServlet 和 Spring 应用上下文，
 * Spring 应用上下文会位于应用程序的 Servlet 上下文之中。
 **/
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * ContextLoaderListener 加载应用中的其他 Bean ,这些 Bean 通常用于驱动应用后端的中间层和数据库组件
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * DispatcherServlet 启动时，自动创建 Spring 应用上下文并配置
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
