package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dto.AuthorityDTO;
import dto.AuthorityListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserMangeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccessController {

    private UserMangeService userService;

    @Autowired
    public void setUserService(UserMangeService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/access", produces = {"application/json;charset=UTF-8"})
    public String access() {
        return "access";
    }

    @RequestMapping("/access.json")
    @ResponseBody
    public String access(HttpSession session,
                         HttpServletRequest request,
                         @RequestParam int limit,
                         @RequestParam int page) {
        Integer userId = (Integer) session.getAttribute("userId");
        String additional = request.getParameter("data");
        AuthorityListDTO authorityListDTO;
        if (additional == null) {
            authorityListDTO = userService.getAuthoritiesOfUserLimit(userId, (page - 1) * limit, limit);
        } else {
            authorityListDTO = userService.searchAuthoritiesOfUserLimit(userId, additional, (page - 1) * limit, limit);
        }
        JSONArray array = new JSONArray();

        for (AuthorityDTO entity : authorityListDTO.getList()) {
            JSONObject object = new JSONObject();
            object.put("userName", entity.getUserName());
            object.put("startDate", entity.getStartDate().toString());
            object.put("endDate", entity.getEndDate().toString());
            object.put("houseId", entity.getHouseId());
            array.add(object);
        }

        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "msg");
        object.put("count", authorityListDTO.getCount());
        object.put("data", array);
        return object.toJSONString();

    }


}
