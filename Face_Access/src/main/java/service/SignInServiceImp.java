package service;

import dao.HouseDao;
import dao.UserDao;
import entity.UserEntity;
import manager.FaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.EncryptInfo;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SignInServiceImp implements SignInService {

    private UserDao userDao;
    private HouseDao houseDao;
    private FaceManager faceManager;

    Logger logger = Logger.getLogger("dsad");

    @Autowired
    public void setHouseDao(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setFaceManager(FaceManager faceManager) {
        this.faceManager = faceManager;
    }

    @Override
    public boolean verifyUsername(String username) {
        Long cnt = userDao.checkUser(username);
        return cnt == 0;
    }

    @Override
    public boolean verifyUser(String username, String password) {
        password = EncryptInfo.MD5(password);
        UserEntity entity = userDao.verifyUser(username, password);
        return entity != null;
    }

    @Override
    public int verifyUserByFace(String img) {
        return faceManager.searchFace(img);
    }

    @Override
    public String getUsername(int userId) {
        return userDao.getUserById(userId).getUserName();
    }

    @Override
    public String getUserType(int userId) {
        UserEntity entity = userDao.getUserById(userId);
        if (0 == entity.getUserType()) {
            List list = houseDao.getHouseIdByOwner(userId);
            return list.size() > 0 ? "OWNER" : "USER";
        } else {
            return "ADMIN";
        }
    }

    @Override
    public int getUserId(String username) {
        return userDao.getUserByName(username).getUserId();
    }

    @Override
    public int getUserIdByTelephone(String telephone) {
        UserEntity entity = userDao.getUserByTelephone(telephone);
        return entity == null ? -1 : entity.getUserId();
    }

    @Override
    public UserEntity getUser(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public int addUser(String username, String telephone, String password) {
        UserEntity entity = new UserEntity();
        entity.setUserName(username);
        entity.setUserPassword(EncryptInfo.MD5(password));
        entity.setUserTelephone(telephone);
        return userDao.addUser(entity);
    }
}
