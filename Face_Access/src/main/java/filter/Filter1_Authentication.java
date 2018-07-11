package filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
@WebFilter(filterName = "Filter1_AuthenticationFilter",
        urlPatterns = {"/access/*", "/authorities/*"})
public class Filter1_Authentication implements Filter {

    private Logger logger = Logger.getLogger("filter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----Filter1_AuthenticationFilter Init----");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        logger.info("Request URI: " + uri);

        String type = (String) request.getSession().getAttribute("type");

        if (type == null) {
            response.sendRedirect(request.getContextPath() + "/signIn");
        } else {

            if ("USER".equals(type)) {
                if (uri.contains("/access")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            if ("OWNER".equals(type)) {
                if (uri.contains("/authorities") || uri.contains("/access")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            if ("ADMIN".equals(type)) {
                if (uri.contains("/admin") || uri.contains("/authorities")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            response.sendRedirect(request.getContextPath() + "/denied");
        }
    }

    @Override
    public void destroy() {
        logger.info("----Filter1_AuthenticationFilter Destroy----");
    }
}
