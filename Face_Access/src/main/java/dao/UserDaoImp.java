package dao;

import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author ACM-PC
 */

@Repository
@DynamicUpdate
public class UserDaoImp implements UserDao {

    private SessionFactory factory;

    private Logger logger = Logger.getLogger("dsd");

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

//    public UserDaoImp() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//    }

    @Override
    public int addUser(UserEntity userEntity) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Integer userID = 0;
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

        if (user.getUserPassword() != null) {
            userEntity.setUserPassword(user.getUserPassword());
        }

        if (user.getUserTelephone() != null) {
            userEntity.setUserTelephone(user.getUserTelephone());
        }

        session.update(userEntity);
        ts.commit();
    }

    @Override
    public Long checkUser(String username) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select count(*) from UserEntity where userName=:A");
        query.setParameter("A", username);
        Long count = (Long) query.uniqueResult();
        session.getTransaction().commit();
        return count;
    }

    @Override
    public UserEntity verifyUser(String username, String password) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserEntity where userName=:name and userPassword=:word");
        query.setParameter("name", username);
        query.setParameter("word", password);
//        password = "'or 1=1#";
//        username = "'or”='";
//        logger.info(username + " " + password);
//        Query query = session.createQuery("from UserEntity where userName=" + username + " and userPassword=" + password);
        UserEntity entity = null;
        List list = query.list();
        if (list.size() > 0) {
            entity = (UserEntity) list.get(0);
        }
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public UserEntity getUserById(int userID) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, userID);
        ts.commit();
        return user;
    }

    @Override
    public UserEntity getUserByName(String username) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserEntity  where userName = :A");
        query.setParameter("A", username);
        UserEntity entity = (UserEntity) query.getSingleResult();
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public UserEntity getUserByTelephone(String telephone) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from UserEntity where userTelephone = :phone");
        query.setParameter("phone", telephone);
        List list = query.list();
        UserEntity entity = null;
        if (list.size() > 0) {
            entity = (UserEntity) list.get(0);
        }
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public List getUsernameList() {
        Session session = factory.openSession();
        session.beginTransaction();
        List usernameList = session.createQuery("select userName from UserEntity").list();
        session.getTransaction().commit();
        return usernameList;
    }
}
