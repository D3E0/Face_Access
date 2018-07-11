package controller;

import com.alibaba.fastjson.JSONObject;
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
import util.Base64Util;
import util.EncryptInfo;
import util.FileUtil;
import util.VerifyCodeProducer;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

    @RequestMapping("/user/update/password")
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
        return object.toJSONString();
    }

    @RequestMapping("/user/update/telephone")
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
        return object.toJSONString();
    }

    @RequestMapping("/user/digitVerifyCode")
    @ResponseBody
    public String getUpdateCode(HttpSession session) {
        String digitVerifyCode = VerifyCodeProducer.getDigitVerifyCode();
        session.setAttribute("digitVerifyCode", digitVerifyCode);
        JSONObject object = new JSONObject();
        object.put("digitVerifyCode", digitVerifyCode);
        return object.toJSONString();
    }

    /**
     * 照片上传
     *
     * @param image
     * @return JSON
     */
    @RequestMapping("/user/upload")
    @ResponseBody
    public String savePicture(@RequestPart("image") MultipartFile image,
                              @RequestParam int userId) {
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "fail");
        logger.info("dsada");
        try {
            File file = new File("D:\\fxml", image.getOriginalFilename());
            logger.info(file.getAbsolutePath());
            image.transferTo(file);
            byte[] imgData = FileUtil.readFileByBytes(file.getAbsolutePath());
            String imgStr = Base64Util.encode(imgData);
            boolean res = userService.updateUserFace(userId, imgStr);
            if (res) {
                object.put("msg", "success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object.toJSONString();
    }

}

