package dao;

import entity.HouseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DynamicUpdate
public class HouseDaoImp implements HouseDao {

    private SessionFactory factory;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<HouseEntity> getHouses(int userId) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from HouseEntity  where user.userId = :id");
        query.setParameter("id", userId);
        List<HouseEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }
    @Override
    public List<HouseEntity> getHouses(int page, int limit) {
        int start=(page-1)*limit;
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from HouseEntity");
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<HouseEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Long counthouse() {
        Session session=factory.openSession();
        Transaction tx = null;
        Long count=null;
        try {
            tx = session.beginTransaction();
            Query q   = session.createQuery("select count(*) from HouseEntity");
            count=(Long)q.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return count;
    }

    @Override
    public String delhouse(int houseid) {
        String back="success";
        Session session=factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            HouseEntity houseEntity=session.get(HouseEntity.class,houseid);
            session.delete(houseEntity);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            back="fail";
        }finally {
            session.close();
        }
        return back;
    }

    @Override
    public String addhouse(HouseEntity houseEntity) {
        String back="success";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(houseEntity);
            tx.commit();
        } catch (Exception e) {
            back="fail";
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return back;
    }
}
