package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.OpenRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RecordMangeService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RecordController {
    RecordMangeService recordService;
    @Autowired
    public void setRecordMangeService(RecordMangeService recordMangeService) {
        this.recordService = recordMangeService;
    }

    @RequestMapping("/records")
    public String showrecordview(){
        return "records";
    }
    @RequestMapping(value="recordsjson", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String recordsjson(@RequestParam (value = "page",defaultValue = "1")String page,@RequestParam (value = "limit",defaultValue = "10")String limit){
        List<OpenRecordEntity> list = recordService.getRecordlist(Integer.parseInt(page),Integer.parseInt(limit));
        JSONArray array = new JSONArray();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (OpenRecordEntity entity : list) {
            JSONObject object = new JSONObject();
            object.put("Id", entity.getOpenId());
            object.put("doorId", entity.getDoorEntity().getDoorId());
            object.put("openDate", dateFormat.format(new Date(entity.getOpenDate().getTime())));
            object.put("openResult", entity.getOpenResult());
            object.put("userID",entity.getUserEntity().getUserId());
            object.put("userName",entity.getUserEntity().getUserName());
            object.put("doorLocation", entity.getDoorEntity().getDoorLocation());
            array.add(object);
        }
        String ans="{\"code\":0,\"msg\":\"\",\"count\":"+recordService.countRecord()+",\"data\":" + array + "}";
        return ans;
    }
}
