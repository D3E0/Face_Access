package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.DoorEntity;
import entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DoorMangeService;
import service.HouseMangeService;
import service.UserMangeService;
import util.EncryptInfo;

import java.util.List;

@Controller
public class HouseController {
    private UserMangeService userService;
    private DoorMangeService doorService;
    private HouseMangeService houseService;
    @Autowired
    public void setDoorService(DoorMangeService doorService) {
        this.doorService = doorService;
    }
    @Autowired
    public void setUserService(UserMangeService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHouseMangeService(HouseMangeService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping("/houses")
    public String houses(){
        return "houses";
    }
    @RequestMapping(value="housesjson", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String gethouses(@RequestParam(value = "page",defaultValue = "1")String page, @RequestParam(value = "limit",defaultValue = "10")String limit){
        List<HouseEntity> list = houseService.gethouselist(Integer.parseInt(page),Integer.parseInt(limit));//Integer.parseInt(page),Integer.parseInt(limit)
        JSONArray array = new JSONArray();
        for (HouseEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("houseId", entity.getHouseId());
            object.put("username", entity.getUser().getUserName());
            object.put("userId",entity.getUser().getUserId());
//            object.put("Ip", entity.getDoorIp());
            array.add(object);
        }
        String ans="{\"code\":0,\"msg\":\"\",\"count\":"+houseService.counthouses()+",\"data\":" + array + "}";
        return ans;
    }
    @RequestMapping("/delhouses")
    @ResponseBody
    public String delhouse(@RequestParam(value = "houseid") String houseid){
        return houseService.delhouse(Integer.parseInt(houseid));
    }
    @RequestMapping("/addhouseview")
    public String addhouseview(){
        return "addhouse";
    }
    @RequestMapping("/addhouse")
    @ResponseBody
    public String addhouse(@RequestParam (value = "houseid")String houseid, @RequestParam (value = "userid")String userid, @RequestParam (value = "housepassword")String housepassword,@RequestParam (value = "doorid")String doorid){
        HouseEntity houseEntity = new HouseEntity();
        houseEntity.setHouseId(Integer.parseInt(houseid));
        houseEntity.setDoor(doorService.getDoorEntity(Integer.parseInt(doorid)));
        houseEntity.setUser(userService.getUserEntity(Integer.parseInt(userid)));
        houseEntity.setHousePassword(EncryptInfo.MD5(housepassword));
        return houseService.addhouse(houseEntity);
    }
}
