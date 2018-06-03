package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//TODO 注册
@Controller
public class SignInController {

    private SignInService signInService;

    @Autowired
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
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
     * 登陆表单 提交至此，登陆成功将用户名与 ID 添加至 Session
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
            session.setAttribute("username", signInService.getUsername(userid));
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
     * 返回注册界面
     *
     * @return register.jsp
     */
    @RequestMapping("/register")
    public String doRegister() {
        return "register";
    }


    /**
     * 注册表单提交处理
     * @param request
     * @return
     */
    @RequestMapping("/processRegister")
    public String processRegister(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        object.put("result", "false");

        String username = request.getParameter("username");
        String userTel = request.getParameter("usertel");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");

        if(password != confirmpassword) {
            return JSON.toJSONString(object);
        }

        int userID = signInService.addUser(username, userTel, password);
        if(userID != 0) {
            object.put("result", "success");
        }

        return JSON.toJSONString(object);

    }


}
