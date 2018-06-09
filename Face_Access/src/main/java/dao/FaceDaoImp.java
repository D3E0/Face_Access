package dao;

import util.Face;


public class FaceDaoImp implements FaceDaoInterface {
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
}
