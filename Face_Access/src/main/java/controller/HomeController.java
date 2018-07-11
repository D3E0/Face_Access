package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * @author ACM-PC
 * 登陆、注册控制
 */

@Controller
public class HomeController {

    private Logger logger = Logger.getLogger("a");

    /**
     * 返回主界面
     *
     * @return
     */
    @RequestMapping("/home")
    public String showHomePage() {
        return "home";
    }

    /**
     * 用户退出，清除 Session, 重定向至登陆界面
     *
     * @param session
     * @return signIn.jsp
     */

    @RequestMapping("/logout")
    public String logout(HttpSession session,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        Enumeration<String> AttributeNames = session.getAttributeNames();
        while (AttributeNames.hasMoreElements()) {
            session.removeAttribute(AttributeNames.nextElement());
        }

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("autoLogin".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/signIn";
    }

    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }
}
