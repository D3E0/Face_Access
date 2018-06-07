package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.HouseEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserMangeService;
import util.DateParse;
import util.EncryptInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ACM-PC
 * Controller 层，调用多个 Service 完成 URL 请求 一个 Controller 控制一到多个视图
 * 不能复用，提供 Service 所需参数
 */

@Controller
public class AuthorityController {

    private UserMangeService userService;

    private Logger logger = Logger.getLogger("heh");

    @Autowired

    public void setUserService(UserMangeService userService) {
        this.userService = userService;
    }

    /**
     * 控制跳转 添加人员权限界面
     *
     * @return addAuthority.jsp
     */
    @RequestMapping("/addAuthority")
    public String saveUser() {
        return "addAuthority";
    }

    /**
     * 返回所有人员 用户名，用于模糊查找
     *
     * @return JSON
     */
    @RequestMapping("/getAllUsername")
    @ResponseBody
    public String searchUserProfile() {
        List<UserEntity> list = userService.getUserList();
        JSONArray array = new JSONArray();
        for (UserEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("username", entity.getUserName());
            array.add(object);
        }
        return JSON.toJSONString(array);
    }

    /**
     * 添加权限时，根据 用户名 返回人员详情
     *
     * @param username
     * @return JSON
     */
    @RequestMapping(value = "/user.json", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserProfile(@RequestParam(value = "username") String username) {
        UserEntity entity = userService.getUserByUsername(username);
        JSONObject object = new JSONObject();
        object.put("userTel", EncryptInfo.encryptTelephone(entity.getUserTelephone()));
        object.put("userId", entity.getUserId());
        return JSON.toJSONString(object);
    }


    /**
     * 返回该业主的所有房间 ID
     *
     * @param id
     * @return JSON
     */
    @RequestMapping("/getHouse")
    @ResponseBody
    public String getHouse(@RequestParam(value = "userId", defaultValue = "0") int id) {
        List<HouseEntity> houseEntities = userService.getHousesByOwner(id);
        JSONArray array = new JSONArray();
        for (HouseEntity entity : houseEntities) {
            JSONObject object = new JSONObject();
            object.put("houseId", entity.getHouseId());
            array.add(object);
        }
        return JSON.toJSONString(array);
    }

    /**
     * 处理用户添加人员权限表单提交
     *
     * @param request
     * @return JSON
     */
    @RequestMapping("/processAddAuthority")
    @ResponseBody
    public String processAddUser(HttpServletRequest request) {

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String remark = request.getParameter("remark");
        Date startSqlDate = DateParse.stringToSql(startDate);
        Date endSqlDate = DateParse.stringToSql(endDate);
        int userId = Integer.parseInt(request.getParameter("userId"));
        int houseId = Integer.parseInt(request.getParameter("houseId"));

        userService.addAuthority(houseId, userId, startSqlDate, endSqlDate, remark);

        JSONObject object = new JSONObject();
        object.put("result", "success");
        return JSON.toJSONString(object);
    }

    /**
     * 处理用户更新权限表单提交
     *
     * @param end
     * @param authorityId
     * @param remark
     * @return
     */
    @RequestMapping("/updateAuthority")
    @ResponseBody
    public String updateAuthority(@RequestParam(value = "end", defaultValue = "0 ") String end,
                                  @RequestParam(value = "id", defaultValue = "0") int authorityId,
                                  @RequestParam(value = "remark") String remark) {
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        Date endDate = DateParse.stringToSql(end);
        userService.updateEndDate(authorityId, endDate);
        userService.updateRemark(authorityId, remark);
        object.put("result", "success");
        return JSON.toJSONString(object);
    }

    /**
     * 更新权限时，返回原授权日期、失效日期、备注
     *
     * @param start
     * @param end
     * @param model
     * @return updateAuthority.jsp
     */
    @RequestMapping("/authorityInfo")
    public String saveDate(@RequestParam(value = "start", defaultValue = "0") String start,
                           @RequestParam(value = "end", defaultValue = "0") String end,
                           @RequestParam(value = "remark") String remark,
                           Model model) {
        model.addAttribute("remark", remark);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "updateAuthority";
    }

    /**
     * 删除用户权限
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteAuthority")
    @ResponseBody
    public String deleteAuthority(HttpServletRequest request) {
        Integer authorityId;
        JSONObject object = new JSONObject();
        object.put("result", "fail");
        try {
            authorityId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JSON.toJSONString(object);
        }

        userService.deleteAuthority(authorityId);

        object.put("result", "success");
        return JSON.toJSONString(object);
    }


}
