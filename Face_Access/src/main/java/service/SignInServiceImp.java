package service;

import dao.UserDao;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.EncryptInfo;

@Service
public class SignInServiceImp implements SignInService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean verifyUsername(String username) {
        UserEntity entity = userDao.getUserByName(username);
        if (entity != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyUser(String username, String password) {
        password = EncryptInfo.MD5(password);
        UserEntity entity = userDao.verifyUser(username, password);
        if (entity != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getUsername(int userId) {
        return userDao.getUserById(userId).getUserName();
    }

    @Override
    public int getUserId(String username) {
        return userDao.getUserByName(username).getUserId();
    }

    @Override
    public UserEntity getUserByTelephone(String telephone) {
        return userDao.getUserByTelephone(telephone);
    }

    @Override
    public int addUser(String username, String telephone, String password) {
        UserEntity entity = new UserEntity();
        entity.setUserName(username);
        entity.setUserPassword(EncryptInfo.MD5(password));
        entity.setUserTelephone(telephone);
        int userID = userDao.addUser(entity);
        return userID;
    }
}
