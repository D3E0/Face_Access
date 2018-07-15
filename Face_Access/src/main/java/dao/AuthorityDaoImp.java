package dao;

import dto.AuthorityListDTO;
import entity.AuthorityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
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

    private SessionFactory factory;

    public AuthorityDaoImp() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

//    @Autowired
//    public void setFactory(SessionFactory factory) {
//        this.factory = factory;
//    }

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

    @Override
    public AuthorityEntity getAuthority(int authorityId) {
        Session session = factory.openSession();
        session.beginTransaction();
        AuthorityEntity entity = session.get(AuthorityEntity.class, authorityId);
        session.getTransaction().commit();
        return entity;
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

    /**
     * 某一用户的权限列表
     *
     * @param userID
     * @return
     */
    @Override
    public AuthorityListDTO getAuthoritiesOfUserLimit(int userID, int start, int offset) {
        Session session = factory.openSession();
        List list;
        Long count = 0L;

        session.beginTransaction();
        String hql = "select new dto.AuthorityDTO( " +
                "house.houseId, user.userName, startDate, endDate) " +
                "from AuthorityEntity where user.userId=:A " +
                "order by startDate";
        String countHql = "select count(*) from AuthorityEntity " +
                "where user.userId = :D";
        Query query = session.createQuery(hql);
        query.setParameter("A", userID);
        query.setFirstResult(start);
        query.setMaxResults(offset);
        list = query.list();

        Query countQuery = session.createQuery(countHql);
        countQuery.setParameter("D", userID);
        count = (Long) countQuery.uniqueResult();
        session.getTransaction().commit();

        AuthorityListDTO authorityListDTO = new AuthorityListDTO();
        authorityListDTO.setList(list);
        authorityListDTO.setCount(count);

        return authorityListDTO;
    }

    @Override
    public AuthorityListDTO searchAuthoritiesOfUserLimit(int userID, String data, int start, int offset) {
        Session session = factory.openSession();
        List list;
        Long count = 0L;
        String hql = "select new dto.AuthorityDTO( house.houseId, " +
                "user.userName, startDate, endDate) " +
                "from AuthorityEntity where " +
                "user.userName like :A and user.userId= :C " +
                "order by startDate";
        String countHql = "select count(*) from AuthorityEntity where " +
                "user.userName like :D and user.userId= :F";

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("A", "%" + data + "%");
        query.setParameter("C", userID);
        query.setFirstResult(start);
        query.setMaxResults(offset);
        list = query.list();

        Query countQuery = session.createQuery(countHql);
        countQuery.setParameter("D", "%" + data + "%");
        countQuery.setParameter("F", userID);
        count = (Long) countQuery.uniqueResult();
        session.getTransaction().commit();

        AuthorityListDTO authorityListDTO = new AuthorityListDTO();
        authorityListDTO.setList(list);
        authorityListDTO.setCount(count);

        return authorityListDTO;
    }

    @Override
    public AuthorityListDTO getAuthoritiesOfOwnerLimit(int ownerID, int start, int offset) {
        Session session = factory.openSession();
        List list;
        Long count = 0L;

        session.beginTransaction();
        String hql = "select new dto.AuthorityDTO( " +
                "house.houseId, user.userName,authorityId, startDate, endDate, remark) " +
                "from AuthorityEntity where house.user.userId=:A " +
                "order by startDate";
        String countHql = "select count(*) from AuthorityEntity " +
                "where house.user.id = :D";
        Query query = session.createQuery(hql);
        query.setParameter("A", ownerID);
        query.setFirstResult(start);
        query.setMaxResults(offset);
        list = query.list();

        Query countQuery = session.createQuery(countHql);
        countQuery.setParameter("D", ownerID);
        count = (Long) countQuery.uniqueResult();
        session.getTransaction().commit();

        AuthorityListDTO authorityListDTO = new AuthorityListDTO();
        authorityListDTO.setList(list);
        authorityListDTO.setCount(count);
        return authorityListDTO;
    }

    @Override
    public AuthorityListDTO searchAuthoritiesOfOwnerLimit(int ownerID, String data, int start, int offset) {
        Session session = factory.openSession();
        List list;
        Long count = 0L;
        String hql = "select new dto.AuthorityDTO( house.houseId, " +
                "user.userName,authorityId, startDate, endDate, remark) " +
                "from AuthorityEntity where " +
                "(user.userName like :A or remark like :B)" +
                " and house.user.userId= :C " +
                "order by startDate";
        String countHql = "select count(*) from AuthorityEntity where " +
                "(user.userName like :D or remark like :E)" +
                "and house.user.userId= :F";

        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("A", "%" + data + "%");
        query.setParameter("B", "%" + data + "%");
        query.setParameter("C", ownerID);
        query.setFirstResult(start);
        query.setMaxResults(offset);
        list = query.list();

        Query countQuery = session.createQuery(countHql);
        countQuery.setParameter("D", "%" + data + "%");
        countQuery.setParameter("E", "%" + data + "%");
        countQuery.setParameter("F", ownerID);
        count = (Long) countQuery.uniqueResult();
        session.getTransaction().commit();

        AuthorityListDTO authorityListDTO = new AuthorityListDTO();
        authorityListDTO.setList(list);
        authorityListDTO.setCount(count);

        return authorityListDTO;
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
