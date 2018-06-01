package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HomeServiceInterface;
import service.UserMangeServiceInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * @author ACM-PC
 */
@Controller
public class HomeController {

    private HomeServiceInterface homeService;
    private UserMangeServiceInterface userService;

    private Logger logger = Logger.getLogger("sd");

    @Autowired
    public void setUserService(UserMangeServiceInterface userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHomeService(HomeServiceInterface homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/processlogin")
    @ResponseBody
    public String processLogin(HttpServletRequest req, HttpSession session) {
        Integer userid = Integer.valueOf(req.getParameter("userid").trim());
        String password = req.getParameter("password").trim();
        logger.info(userid + " " + password);
        JSONObject object = new JSONObject();
        if (homeService.validateUser(userid, password)) {
            session.setAttribute("userid", userid);
            object.put("result", "success");
        } else {
            object.put("result", "fail");
        }
        return JSON.toJSONString(object);
    }

    @RequestMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        logger.info("here");
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            return "redirect:/static/login.html";
        }
        model.addAttribute("userid", userid);
        return "home";
    }

    @RequestMapping("/validateusrename")
    @ResponseBody
    public String validaetUsrename(HttpServletRequest req) {
        String userid = req.getParameter("userid").trim();
        JSONObject object = new JSONObject();
        if (homeService.validateUsername(userid)) {
            object.put("result", "success");
        } else {
            object.put("result", "fail");
        }
        return JSON.toJSONString(object);
    }


}
