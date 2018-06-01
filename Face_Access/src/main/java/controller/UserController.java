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
import service.UserMangeServiceInterface;
import util.DateParse;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
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
     * 查看个人资料
     *
     * @param id
     * @param model
     * @return userProfile.jsp
     */
    @RequestMapping("/user")
    public String showUserProfile(@RequestParam(value = "id", defaultValue = "-1") int id, Model model) {
        UserEntity entity = userService.getUserEntity(id);
        model.addAttribute(entity);
        return "userProfile";
    }


    /**
     * 添加人员时，查看人员详情
     *
     * @param id
     * @return JSON
     */
    @RequestMapping("/user.json")
    @ResponseBody
    public String showUserProfile(@RequestParam(value = "id", defaultValue = "0") int id) {
        UserEntity entity = userService.getUserEntity(id);
        JSONObject object = new JSONObject();
        object.put("username", entity.getUserName());
        object.put("usertel", entity.getUserTelephone());
        return JSON.toJSONString(object);
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
        return JSON.toJSONString(array);
    }

    /**
     * 控制跳转，跳转到人员管理界面
     *
     * @return users.jsp
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
     * 控制跳转 添加人员界面
     *
     * @return adduser.jsp
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
     * 根据 authorityId 更新用户出入门失效时间
     *
     * @param end
     * @param id
     * @return
     */
    @RequestMapping("/updateAuthority")
    @ResponseBody
    public String updateAuthority(@RequestParam(value = "end", defaultValue = "0") String end,
                                  @RequestParam(value = "id", defaultValue = "0") String id) {
        Date endDate = DateParse.stringToSql(end);
        int authorityId = Integer.parseInt(id);
        userService.updateAuthorityOfHouse(authorityId, endDate);
        JSONObject object = new JSONObject();
        object.put("result", "success");
        return JSON.toJSONString(object);
    }

    /**
     * 日期选择
     *
     * @param start
     * @param end
     * @param model
     * @return chooseDate.jsp
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
    @RequestMapping("/processAddUser")
    @ResponseBody
    public String processUser(HttpServletRequest request) {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Date startSqlDate = DateParse.stringToSql(startDate);
        Date endSqlDate = DateParse.stringToSql(endDate);
        int userId = Integer.parseInt(request.getParameter("userid"));

        logger.info(userId + " " + startDate + " " + endDate);

        userService.addAuthorityOfHouse(12605, userId, startSqlDate, endSqlDate);

        JSONObject object = new JSONObject();
        object.put("result", "success");
        return JSON.toJSONString(object);
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

