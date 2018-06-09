package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.Face;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/checkpic")
    @ResponseBody
    public String checkpic(@RequestParam(value = "img",defaultValue = "0") String imgStr){
        imgStr=imgStr.replaceFirst("data:image/jpeg;base64,", "");
        return Face.search(imgStr);
    }
}
