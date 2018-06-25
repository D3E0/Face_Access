package dao;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;
import util.Face;

@Repository
public class FaceDaoImp implements FaceDao {
    private String surl="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/";
    @Override
    public String addface(String userid, String img) {
        String url = surl+"add";
        return Face.dourl(url,userid,img);
    }

    @Override
    public String delface(String userid) {
        String url = surl+"delete";
        return Face.dourl(url,userid,null);
    }

    @Override
    public String updateface(String userid, String img) {
        String url =  surl+"update";
        return Face.dourl(url,userid,img);
    }

    @Override
    public String searchface(String img) {
        return Face.search(img);
    }
}
