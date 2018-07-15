package filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.SignInService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.logging.Logger;

@Controller
@WebFilter(filterName = "Filter0_UniversalFilter",
        urlPatterns = "/*")
public class Filter0_SignIn implements Filter {

    private Logger logger = Logger.getLogger("filter");
    private SignInService service;
    private static final String[] INITPARAM = {
            "/signIn", "/register", "/logout", "/denied", "/static", "/error"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        service = cxt.getBean(SignInService.class);
        logger.info("----Filter0_SignInFilter Init----");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        //logger.info("Request URI: " + uri);

        // 设置请求的字符集（post请求方式有效）
        request.setCharacterEncoding("utf-8");
        String path = request.getContextPath() + "/";
        if (path.equals(uri)) {
            chain.doFilter(request, response);// 非拦截请求，放行
            return;
        }

        for (String param : INITPARAM) {
            if (uri.contains(param)) {
                chain.doFilter(request, response);// 非拦截请求，放行
                return;
            }
        }

        logger.info("Request URI: " + uri);

        if (request.getSession().getAttribute("userId") != null) {
            // 已经登录，放行 logger.info("Target Url, Already Login");
            chain.doFilter(request, response);
        } else {
            Cookie cookies[] = request.getCookies();
            String val = null;
            for (Cookie cookie : cookies) {
                if ("autoLogin".equals(cookie.getName())) {
                    val = cookie.getValue();
                }
            }
            if (val != null) {
                String[] temp = val.split("\\.");
                if (temp.length >= 2) {
                    String username = temp[0];
                    String urlDecodeStr = URLDecoder.decode(temp[1]);
                    String password = new String(Base64.getDecoder().decode(urlDecodeStr));
                    if (service.verifyUser(username, password)) {
                        int userId = service.getUserId(username);
                        HttpSession session = request.getSession();
                        session.setAttribute("userId", userId);
                        session.setAttribute("type", service.getUserType(userId));
                        session.setAttribute("username", service.getUsername(userId));
                        logger.info("-----" + username + " " + password + " Match Success-----");
                        chain.doFilter(request, response);
                        return;
                    }
                    logger.info("-----" + username + " " + password + " Does Not Match-----");
                }
            }
            // 重定向到登录页面 logger.info("Target Url, Not Login && No Cookies, Send Redirect");
            request.getSession().setAttribute("url", uri);
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    public void destroy() {
        logger.info("----Filter0_SignInFilter Destroy----");
    }
}
