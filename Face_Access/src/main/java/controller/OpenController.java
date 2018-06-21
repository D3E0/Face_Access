package controller;

import dao.FaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.FaceMangeService;
import util.Base64Util;
import util.Face;
import util.FileUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class OpenController {
    private FaceMangeService faceMangeService;
    @Autowired
    public void setFaceMangeService(FaceMangeService faceMangeService) {
        this.faceMangeService = faceMangeService;
    }

    @RequestMapping("/open")
    @ResponseBody
    public String open(HttpServletRequest request, @RequestParam(value = "img")MultipartFile img, @RequestParam(value = "doorid")String doorid) throws IOException {
        if(!img.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/static/images");
            //上传文件名
            String filename = img.getOriginalFilename();
            File file = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            img.transferTo(file);
            byte[] imgData = FileUtil.readFileByBytes(file.getPath());
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            faceMangeService.searchface(imgParam);
            return "success";
        } else {
            return "error";
        }
    }
}
