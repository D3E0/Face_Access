package dao;

import entity.AuthorityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

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
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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
    public void updateEndDate(int id, Date endDate) {
        Session session = factory.openSession();
        session.beginTransaction();
        AuthorityEntity entity = session.find(AuthorityEntity.class, id);
        entity.setEndDate(endDate);
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public int updateRemark(int authorityID, String remark) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update AuthorityEntity set remark=:remark" +
                " where authorityId=:id");
        query.setParameter("remark", remark);
        query.setParameter("id", authorityID);
        int count = query.executeUpdate();
        session.getTransaction().commit();
        return count;

    }

    /**
     * 某一用户在某一房间的权限
     *
     * @param userID
     * @param houseID
     * @return
     */
    @Override
    public AuthorityEntity getAuthority(int userID, int houseID) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AuthorityEntity where user.userId = :A and house.houseId= :B");
        query.setParameter("A", userID);
        query.setParameter("B", houseID);
        List list = query.list();
        AuthorityEntity entity = null;
        if (list.size() > 0) {
            entity = (AuthorityEntity) list.get(0);
        }
        session.getTransaction().commit();
        return entity;
    }

    /**
     * 某一用户的权限列表
     *
     * @param userID
     * @return
     */
    @Override
    public List getAuthoritiesOfUser(int userID) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from AuthorityEntity where user.userId =" + userID);
        List list = query.list();
        session.getTransaction().commit();
        return list;
    }

    /**
     * 该房间下所有人员
     *
     * @param houseID
     * @return
     */
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
    public List searchAuthoritiesOfHouse(int houseID, String data) {
        Session session = factory.openSession();
        session.beginTransaction();
//        String hql = "from AuthorityEntity where (user.userName like '%戴%' or remark like '%戴%')" +
////                " and house.houseId = 1608 ";
        String hql = "from AuthorityEntity where (user.userName like :A or remark like :B)" +
                " and house.houseId = :C ";
        Query query = session.createQuery(hql);

        query.setParameter("A", "%" + data + "%");
        query.setParameter("B", "%" + data + "%");
        query.setParameter("C", houseID);
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
