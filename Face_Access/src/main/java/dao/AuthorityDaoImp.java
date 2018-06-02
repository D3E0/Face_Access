package dao;

import entity.AuthorityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author ACM-PC
 * DAO 层 原子级的数据操作
 * 高级 DAO 模式中，多个 DAO 一般会继承一个基类，实现工厂模式
 */
@Repository
@DynamicUpdate
public class AuthorityDaoImp implements AuthorityDao {

    @Autowired
    private SessionFactory factory;

    public AuthorityDaoImp() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public int addAuthority(AuthorityEntity authorityEntity) {
        Session session = factory.openSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(authorityEntity);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public void deleteAuthority(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        AuthorityEntity entity = session.find(AuthorityEntity.class, id);
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public void updateAuthority(int id, Date endDate) {
        Session session = factory.openSession();
        session.beginTransaction();
        AuthorityEntity entity = session.find(AuthorityEntity.class, id);
        entity.setEndDate(endDate);
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public AuthorityEntity getAuthority(int userID, int houseID) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AuthorityEntity where user.userId = :A and house.houseId= :B");
        query.setParameter("A", userID);
        query.setParameter("B", houseID);
        AuthorityEntity entity = (AuthorityEntity) query.list().get(0);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public List getAuthoritiesOfUser(int userID) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AuthorityEntity where user.userId =" + userID);
        List list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public List getAuthoritiesOfHouse(int houseID) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AuthorityEntity where house.houseId =" + houseID);
        List list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public List getAuthorities() {
        Session session = factory.openSession();
        session.beginTransaction();
        List list = session.createQuery("from AuthorityEntity ").list();
        session.getTransaction().commit();
        return list;
    }
}
