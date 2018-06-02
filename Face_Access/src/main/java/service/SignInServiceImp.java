package service;

import dao.UserDao;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInServiceImp implements SignInService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean verifyUserId(int userId) {
        UserEntity entity = userDao.findUser(userId);
        if (entity != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyUser(int userId, String password) {
        UserEntity entity = userDao.verifyUser(userId, password);
        if (entity != null) {
            return true;
        }
        return false;
    }
}
