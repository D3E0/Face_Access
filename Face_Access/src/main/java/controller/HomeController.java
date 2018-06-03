package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserMangeServiceImp;

import javax.servlet.http.HttpSession;

/**
 * @author ACM-PC
 * 登陆、注册控制
 */

//TODO  注册
@Controller
public class HomeController {

    private UserMangeServiceImp userService;

    @Autowired
    public void setUserService(UserMangeServiceImp userService) {
        this.userService = userService;
    }

    /**
     * 主界面，判断用户是否登陆，没有登陆重定向至登陆界面
     *
     * @param session
     * @return
     */
    @RequestMapping("/home")
    public String showHomePage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
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
        session.removeAttribute("username");
        return "redirect:/login";
    }


}
