package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.AuthorityEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.UserMangeService;
import util.EncryptInfo;
import util.VerifyCodeProducer;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {

    private UserMangeService userService;

    @Autowired
    public void setUserService(UserMangeService userService) {
        this.userService = userService;
    }

    Logger logger = Logger.getLogger("UserController");

    /**
     * 查看个人资料
     *
     * @param id
     * @param model
     * @return userProfile.jsp
     */
    @RequestMapping("/user")
    public String showUserProfile(@RequestParam(value = "id", defaultValue = "-1") int id,
                                  Model model) {
        UserEntity entity = userService.getUserEntity(id);
        entity.setUserTelephone(EncryptInfo.encryptTelephone(entity.getUserTelephone()));
        model.addAttribute(entity);
        return "userProfile";
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam int userId,
                                 @RequestParam String oldPassword,
                                 @RequestParam String password) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        int result = userService.updatePassword(userId, password, oldPassword);
        if (result == 1) {
            object.put("result", "success");
        }
        return JSON.toJSONString(object);
    }

    @RequestMapping("/updateTelephone")
    @ResponseBody
    public String updateTelephone(@RequestParam int userId,
                                  @RequestParam String verifyCode,
                                  @RequestParam String telephone,
                                  HttpSession session) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        String digitVerifyCode = (String) session.getAttribute("digitVerifyCode");
        if (verifyCode.equals(digitVerifyCode)) {
            userService.updateTelephone(userId, telephone);
            object.put("result", "success");
            object.put("telephone", EncryptInfo.encryptTelephone(telephone));
        }
        return JSON.toJSONString(object);
    }

    @RequestMapping("/getDigitVerifyCode")
    @ResponseBody
    public String getUpdateCode(HttpSession session) {
        String digitVerifyCode = VerifyCodeProducer.getDigitVerifyCode();
        session.setAttribute("digitVerifyCode", digitVerifyCode);
        JSONObject object = new JSONObject();
        object.put("digitVerifyCode", digitVerifyCode);
        return JSON.toJSONString(object);
    }

    /**
     * 照片上传
     *
     * @param image
     * @return JSON
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String savePicture(@RequestPart("image") MultipartFile image) {
        logger.info(String.valueOf(image.getSize()));
//        try {
//            image.transferTo(new File(image.getOriginalFilename()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "{\"code\":0,\"msg\":\"success\"}";
    }

}

