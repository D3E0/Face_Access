package manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;
import util.Face;

@Repository
public class FaceManagerImp implements FaceManager {
    private String surl = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/";

    @Override
    public String addface(String userid, String img) {
        String url = surl + "add";
        return Face.dourl(url, userid, img);
    }

    @Override
    public String delface(String userid) {
        String url = surl + "delete";
        return Face.dourl(url, userid, null);
    }

    @Override
    public Boolean updateFace(String userId, String img) {
        String url = surl + "update";
        String result = Face.dourl(url, userId, img);
        JSONObject object = JSON.parseObject(result);
        return "SUCCESS".equals(object.get("error_msg"));
    }

    @Override
    public Boolean searchFace(String img) {
        String str = Face.search(img);
        if (str != null) {
            str = str.replaceFirst("result:", "");
        }
        JSONObject jsonObject = JSON.parseObject(str);
        jsonObject = jsonObject.getJSONObject("result");
        JSONArray array = jsonObject.getJSONArray("user_list");
        jsonObject = array.getJSONObject(0);
        double ans = jsonObject.getDouble("score");
        return ans > 80;
    }
}
