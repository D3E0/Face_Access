package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.DoorEntity;
import entity.HouseEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DoorMangeService;
import service.HouseMangeService;
import service.UserMangeService;
import util.EncryptInfo;

import javax.persistence.criteria.CriteriaBuilder;
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
    public String gethouses(@RequestParam(value = "page",defaultValue = "1")String page, @RequestParam(value = "limit",defaultValue = "10")String limit,@RequestParam (value = "keyword",defaultValue = "")String keyword){
        List<HouseEntity> list =null;
        if (keyword.equals(""))
        list= houseService.gethouselist(Integer.parseInt(page),Integer.parseInt(limit));//Integer.parseInt(page),Integer.parseInt(limit)
        else
            list=houseService.getHouseListForSearch(Integer.parseInt(page),Integer.parseInt(limit),keyword);
        JSONArray array = new JSONArray();
        for (HouseEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("houseId", entity.getHouseId());
            object.put("username", entity.getUser().getUserName());
//            object.put("userId",entity.getUser().getUserId());
//            object.put("doorId",entity.getDoor().getDoorId());
            object.put("doorlocation",entity.getDoor().getDoorLocation());
//            object.put("Ip", entity.getDoorIp());
            array.add(object);
        }
        String ans="{\"code\":0,\"msg\":\"\",\"count\":"+houseService.counthouses()+",\"data\":" + array + "}";
        return ans;
    }
    @RequestMapping("/delhouse")
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
        UserEntity userEntity=userService.getUserEntity(Integer.parseInt(userid));
        if (userEntity==null){
            return "wronguid";
        }
        DoorEntity doorEntity=doorService.getDoorEntity(Integer.parseInt(doorid));
        if (doorEntity==null){
            return "wrongdid";
        }
        houseEntity.setHouseId(Integer.parseInt(houseid));
        houseEntity.setDoor(doorEntity);
        houseEntity.setUser(userEntity);
        if (housepassword!=null)
        houseEntity.setHousePassword(EncryptInfo.MD5(housepassword));
        return houseService.addhouse(houseEntity);
    }
    @RequestMapping("/updatehouseview")
    public String updatehouseview(@RequestParam (value = "houseid")String houseid,@RequestParam (value = "userid")String userid, @RequestParam (value = "doorid")String doorid, Model model){
        model.addAttribute("houseid",houseid);
        return "updatehouse";
    }

    @RequestMapping("/updatehouse")
    @ResponseBody
    public String updatehouse(@RequestParam (value = "houseid")String houseid, @RequestParam (value = "userid")String userid, @RequestParam (value = "housepassword")String housepassword,@RequestParam (value = "doorid")String doorid){
        HouseEntity houseEntity = new HouseEntity();
        UserEntity userEntity=userService.getUserEntity(Integer.parseInt(userid));
        if (userEntity==null){
            return "wronguid";
        }
        DoorEntity doorEntity=doorService.getDoorEntity(Integer.parseInt(doorid));
        if (doorEntity==null){
            return "wrongdid";
        }
        houseEntity.setHouseId(Integer.parseInt(houseid));
        houseEntity.setDoor(doorEntity);
        houseEntity.setUser(userEntity);
        if (housepassword!=null)
            houseEntity.setHousePassword(EncryptInfo.MD5(housepassword));
        return houseService.updatehousepwd(houseEntity);
    }

}
