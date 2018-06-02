package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ACM-PC
 * 登陆、注册控制
 */

//TODO 保存用户信息
@Controller
public class HomeController {

    /**
     * 主界面，判断用户是否登陆，没有登陆重定向至登陆界面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            return "redirect:/login";
        }
        model.addAttribute("userid", userid);
        return "home";
    }

    /**
     * 用户退出，清除 Session, 重定向至登陆界面
     *
     * @param session
     * @return login.jsp
     */

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.removeAttribute("userid");
        return "redirect:/login";
    }


}
