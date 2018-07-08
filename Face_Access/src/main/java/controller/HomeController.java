package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ACM-PC
 * 登陆、注册控制
 */

@Controller
public class HomeController {

    /**
     * 主界面，判断用户是否登陆，没有登陆重定向至登陆界面
     *
     * @param session
     * @return
     */
    @RequestMapping("/home")
    public String showHomePage(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/signIn";
        }

        return "home";
    }

    /**
     * 用户退出，清除 Session, 重定向至登陆界面
     *
     * @param session
     * @return signIn.jsp
     */

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/signIn";
    }


}
