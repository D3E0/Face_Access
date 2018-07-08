package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class SignInController {

    private SignInService signInService;

    private Logger logger = Logger.getLogger("us");

    @Autowired
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    /**
     * 返回登陆界面
     *
     * @return signIn.jsp
     */
    @RequestMapping("/signIn")
    public String doSignIn() {
        return "signIn";
    }

    /**
     * 登陆表单 提交至此，登陆成功将用户名与 ID 添加至 Session
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/processSignIn")
    @ResponseBody
    public String processSignIn(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        if (signInService.verifyUser(username, password)) {
            int userId = signInService.getUserId(username);
            session.setAttribute("userId", userId);
            session.setAttribute("type", signInService.getUserType(userId));
            session.setAttribute("username", signInService.getUsername(userId));
            object.put("result", "success");
        }
        return object.toJSONString();
    }

    /**
     * 手机号、验证码登陆
     *
     * @param telephone
     * @param verifyCode
     * @param session
     * @return
     */
    @RequestMapping("/processSignInByTelephone")
    @ResponseBody
    public String processSignInByTelephone(@RequestParam String telephone,
                                           @RequestParam String verifyCode,
                                           HttpSession session) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        String digitVerifyCode = (String) session.getAttribute("digitVerifyCode");
        if (verifyCode.equals(digitVerifyCode)) {
            int userId = signInService.getUserIdByTelephone(telephone);
            session.setAttribute("userId", userId);
            session.setAttribute("type", signInService.getUserType(userId));
            session.setAttribute("username", signInService.getUsername(userId));
            object.put("result", "success");
        }
        return object.toJSONString();
    }

    /**
     * 人脸登陆
     *
     * @param imgStr
     * @param session
     * @return
     */
    @RequestMapping("/processSignInByFace")
    @ResponseBody
    public String processSignInByFace(@RequestParam(value = "img") String imgStr,
                                      HttpSession session) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        imgStr = imgStr.replaceFirst("data:image/jpeg;base64,", "");
        int id = signInService.verifyUserByFace(imgStr);
        if (id != -1) {
            session.setAttribute("userId", id);
            session.setAttribute("username", signInService.getUsername(id));
            session.setAttribute("type", signInService.getUserType(id));
            object.put("result", "success");
        }
        return object.toJSONString();
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
     *
     * @param username
     * @param telephone
     * @param verifyCode
     * @param password
     * @param confirm
     * @param session
     * @return
     */
    @RequestMapping("/processRegister")
    @ResponseBody
    public String processRegister(@RequestParam String username,
                                  @RequestParam String telephone,
                                  @RequestParam String verifyCode,
                                  @RequestParam String password,
                                  @RequestParam String confirm,
                                  HttpSession session) {
        JSONObject object = new JSONObject();
        object.put("result", "false");
        String digitVerifyCode = (String) session.getAttribute("digitVerifyCode");

        if (password.equals(confirm) && verifyCode.equals(digitVerifyCode)) {
            int userID = signInService.addUser(username, telephone, password);
            if (userID != 0) {
                object.put("result", "success");
            }
        }
        return object.toJSONString();
    }

    /**
     * 验证 username 是否存在
     *
     * @param req
     * @return JSON
     */
    @RequestMapping("/verify")
    @ResponseBody
    public String validateUsername(HttpServletRequest req) {
        String username = req.getParameter("username");
        JSONObject object = new JSONObject();
        if (signInService.verifyUsername(username)) {
            object.put("result", "success");
        } else {
            object.put("result", "fail");
        }
        return object.toJSONString();
    }

}
