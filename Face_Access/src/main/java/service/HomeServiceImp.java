package service;


import dao.UserDaoInterface;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImp implements HomeServiceInterface {

    private UserDaoInterface userDao;

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean validateUser(int userId, String password) {

        UserEntity entity = userDao.findUser(userId);
        if (entity != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateUsername(String username) {
        return true;
    }
}
