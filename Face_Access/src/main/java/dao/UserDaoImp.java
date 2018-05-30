package dao;

import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */

@Repository
@DynamicUpdate
public class UserDaoImp implements UserDaoInterface {


    private SessionFactory factory;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public UserDaoImp() {
    }

    @Override
    public int addUser(UserEntity userEntity) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Integer userID = null;
        userID = (Integer) session.save(userEntity);
        ts.commit();
        return userID;
    }

    @Override
    public void deleteUser(int userID) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, userID);
        session.delete(user);
        ts.commit();
    }

    @Override
    public void updateUser(UserEntity user) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();

        UserEntity userEntity = session.get(UserEntity.class, user.getUserId());

        if (user.getUserName() != null) {
            userEntity.setUserName(user.getUserName());
        }

        if (user.getUserFace() != null) {
            userEntity.setUserFace(user.getUserFace());
        }

        if (user.getUserPassword() != null) {
            userEntity.setUserPassword(user.getUserPassword());
        }

        session.update(userEntity);
        ts.commit();
    }

    @Override
    public UserEntity findUser(int userID) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, userID);
        ts.commit();
        return user;
    }

    @Override
    public List<UserEntity> getUserList() {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        List<UserEntity> userList = session.createQuery("from UserEntity").list();
        ts.commit();
        return userList;
    }
}
