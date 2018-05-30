package controller;

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
import service.UserMangeServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {

    private UserMangeServiceInterface userService;

    @Autowired
    public void setUserService(UserMangeServiceInterface userService) {
        this.userService = userService;
    }

    Logger logger = Logger.getLogger("UserController");


    @RequestMapping("/user")
    public String showUserProfile(@RequestParam(value = "id", defaultValue = "-1") int id, Model model) {
        UserEntity entity = userService.getUserEntity(id);
        model.addAttribute(entity);
        return "userProfile";
    }

    @RequestMapping("/getUserId")
    @ResponseBody
    public String searchUserProfile() {
        List<UserEntity> list = userService.getUserEntity();
        JSONArray array = new JSONArray();
        for (UserEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("name", entity.getUserName());
            array.add(object);
        }
        return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" + array + "}";
    }

    @RequestMapping("/users")
    public String showUsers() {
        return "users";
    }

    /**
     * @return json 字段，包含请求 houseID 下的人员信息以及相关授权信息
     */
    @RequestMapping("/users.json")
    @ResponseBody
    public String showUserJson() {
        List<AuthorityEntity> list = userService.getAuthoritiesOfHouse(12605);
        JSONArray array = new JSONArray();
        for (AuthorityEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("userId", entity.getUser().getUserId());
            object.put("userName", entity.getUser().getUserName());
            object.put("userTelephone", entity.getUser().getUserTelephone());
            object.put("startDate", entity.getStartDate().toString());
            object.put("endDate", entity.getEndDate().toString());
            object.put("authorityId", entity.getAuthorityId());
            array.add(object);
        }

        return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" + array + "}";

    }

    @RequestMapping("/adduser")
    public String saveUser() {
        return "adduser";
    }

    @RequestMapping("/updateuser")
    public String updateUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
//        model.addAttribute("user", )
        return "adduser";
    }

    @RequestMapping("/choosedate")
    public String saveDate(@RequestParam(value = "start", defaultValue = "0") String start,
                           @RequestParam(value = "end", defaultValue = "0") String end,
                           Model model) {
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "chooseDate";
    }


    @RequestMapping("/processuser")
    public String processUser(HttpServletRequest request) {
        int id = 1;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(request.getParameter("username"));
        userEntity.setUserTelephone(request.getParameter("usertel"));
        String str = request.getParameter("authorityDate");
        String date = request.getParameter("endDate");

        logger.info(userEntity.toString() + " " + str + " " + date);
        return "redirect:/user?id=" + id;
    }


    /**
     * 照片上传
     * @param image
     * @return
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

