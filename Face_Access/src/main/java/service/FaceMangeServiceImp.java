package service;

import dao.FaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaceMangeServiceImp implements FaceMangeService{
    private FaceDao faceDao;
    @Autowired
    public void setFaceDao(FaceDao faceDao) {
        this.faceDao = faceDao;
    }

    @Override
    public String addface(String userid, String img) {
        System.out.println(faceDao);
        return faceDao.addface(userid,img);
    }

    @Override
    public String delface(String userid) {
        return faceDao.delface(userid);
    }

    @Override
    public String updateface(String userid, String img) {
        return faceDao.updateface(userid,img);
    }

    @Override
    public String searchface(String img) {
        return faceDao.searchface(img);
    }
}
