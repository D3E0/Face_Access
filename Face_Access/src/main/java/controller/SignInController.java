package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    private SignInService signInService;

    @Autowired
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    /**
     * 登陆表单 提交至此
     *
     * @param req
     * @param session
     * @return JSON
     */
    @RequestMapping("/processlogin")
    @ResponseBody
    public String processLogin(HttpServletRequest req, HttpSession session) {
        Integer userid;
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        try {
            userid = Integer.parseInt(req.getParameter("userid").trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JSON.toJSONString(object);
        }
        String password = req.getParameter("password").trim();

        if (signInService.verifyUser(userid, password)) {
            session.setAttribute("userid", userid);
            object.put("result", "success");
        }
        return JSON.toJSONString(object);
    }


    /**
     * 验证用户 ID 是否存在
     *
     * @param req
     * @return JOSN
     */
    @RequestMapping("/verify")
    @ResponseBody
    public String validateUsername(HttpServletRequest req) {
        Integer userid = Integer.valueOf(req.getParameter("userid"));
        JSONObject object = new JSONObject();
        if (signInService.verifyUserId(userid)) {
            object.put("result", "success");
        } else {
            object.put("result", "fail");
        }
        return JSON.toJSONString(object);
    }

    /**
     * 返回登陆界面
     *
     * @return login.jsp
     */
    @RequestMapping("/login")
    public String doLogin() {
        return "login";
    }


    /**
     * 返回注册界面
     *
     * @return register.jsp
     */
    @RequestMapping("/register")
    public String doRegister() {
        return "register";
    }

}
