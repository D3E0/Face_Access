package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FaceMangeService;
import util.Face;

@Controller
public class LoginController {
    private FaceMangeService faceService;
    @Autowired
    public void setFaceService(FaceMangeService faceService) {
        this.faceService = faceService;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/checkpic")
    @ResponseBody
    public String checkpic(@RequestParam(value = "img",defaultValue = "0") String imgStr){
        imgStr=imgStr.replaceFirst("data:image/jpeg;base64,", "");
        return faceService.searchface(imgStr);
    }
}
