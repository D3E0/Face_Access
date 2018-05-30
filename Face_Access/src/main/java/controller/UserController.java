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


    /**
     * 人员详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/user")
    public String showUserProfile(@RequestParam(value = "id", defaultValue = "-1") int id, Model model) {
        UserEntity entity = userService.getUserEntity(id);
        model.addAttribute(entity);
        return "userProfile";
    }

    /**
     * 返回所有人员 ID，用于模糊查找
     *
     * @return JSON
     */
    @RequestMapping("/getAllUserId")
    @ResponseBody
    public String searchUserProfile() {
        List<UserEntity> list = userService.getUserList();
        JSONArray array = new JSONArray();
        for (UserEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("id", entity.getUserId());
            array.add(object);
        }
        return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":" + array + "}";
    }

    /**
     * @return
     */
    @RequestMapping("/users")
    public String showUsers() {
        return "users";
    }

    /**
     * 返回包含请求 houseID 下的人员信息以及相关授权信息，
     *
     * @return JSON
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

    /**
     * 打输
     *
     * @return
     */
    @RequestMapping("/adduser")
    public String saveUser() {
        return "adduser";
    }

    /**
     * @param id
     * @param model
     * @return 控制跳转
     */
    @RequestMapping("/updateuser")
    public String updateUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
//        model.addAttribute("user", )
        return "adduser";
    }

    /**
     * 日期选择 iframe交互中介
     *
     * @param start
     * @param end
     * @param model
     * @return
     */
    @RequestMapping("/choosedate")
    public String saveDate(@RequestParam(value = "start", defaultValue = "0") String start,
                           @RequestParam(value = "end", defaultValue = "0") String end,
                           Model model) {
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "chooseDate";
    }

    /**
     * 用户添加人员表单提交处理
     *
     * @param request
     * @return
     */
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
     *
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

