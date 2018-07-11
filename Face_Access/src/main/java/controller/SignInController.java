package controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SignInService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.logging.Logger;

@Controller
public class SignInController {

    private SignInService signInService;

    private Logger logger = Logger.getLogger("SignInController");

    @Autowired
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    /**
     * 返回登陆界面
     *
     * @return signIn.jsp
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String doSignIn() {
        return "signIn";
    }

    /**
     * 登陆表单 提交至此，登陆成功将用户名与 ID 添加至 Session
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/signIn/process/username")
    @ResponseBody
    public String processSignIn(@RequestParam String username, @RequestParam String password,
                                HttpSession session, HttpServletResponse response) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        if (signInService.verifyUser(username, password)) {
            int userId = signInService.getUserId(username);
            session.setAttribute("userId", userId);
            session.setAttribute("type", signInService.getUserType(userId));
            session.setAttribute("username", signInService.getUsername(userId));
            logger.info("-----" + username + " " + password + " Match Success-----");
            sendCookies(response, username, password);
            object.put("result", "success");
        } else {
            logger.info("-----" + username + " " + password + " Does Not Match-----");
        }
        return object.toJSONString();
    }

    private void sendCookies(HttpServletResponse response, String username, String password) {
        String encodeStr = Base64.getEncoder().encodeToString(password.getBytes());
        String urlEncodeStr = URLEncoder.encode(encodeStr);
        Cookie cookie = new Cookie("autoLogin", username + "." + urlEncodeStr);
        cookie.setMaxAge(24 * 60 * 60);
        // 工程文件就是在根目录下 request.getContextPath() 为 ""
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("-----Add Cookies Success-----");
    }

    @RequestMapping("/signIn/cookie")
    public String addCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "test");
        cookie.setMaxAge(24 * 60 * 60);
        // 工程文件就是在根目录下 request.getContextPath() 为 ""
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("-----" + cookie.getPath());
        return "signIn";
    }

    /**
     * 手机号、验证码登陆
     *
     * @param telephone
     * @param verifyCode
     * @param session
     * @return
     */
    @RequestMapping("/signIn/process/telephone")
    @ResponseBody
    public String processSignInByTelephone(@RequestParam String telephone,
                                           @RequestParam String verifyCode,
                                           HttpSession session,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
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
    @RequestMapping("/signIn/process/image")
    @ResponseBody
    public String processSignInByFace(@RequestParam(value = "img") String imgStr,
                                      HttpSession session,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
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
    @RequestMapping("/register/process")
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
    @RequestMapping("/register/verify")
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