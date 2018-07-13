package dao;

import entity.HouseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    public List<Integer> getHouseIdByOwner(int ownerId) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select houseId from HouseEntity where user.userId = :id");
        query.setParameter("id", ownerId);
        List<Integer> list = query.list();
        session.getTransaction().commit();
        return list;
    }
    @Override
    public List<HouseEntity> getHouseList(int page, int limit) {
        int start=(page-1)*limit;
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select new HouseEntity(houseId,housePassword,door.doorLocation,user.userName) from HouseEntity");
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<HouseEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public List<HouseEntity> getHouseListForSearch(int page, int limit, String keyword) {
        int start=(page-1)*limit;
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select new HouseEntity(houseId,housePassword,door.doorLocation,user.userName) from HouseEntity where door.doorLocation like :A or user.userName like :B ");
        query.setFirstResult(start);
        query.setMaxResults(limit);
        query.setParameter("A","%"+keyword+"%");
        query.setParameter("B","%"+keyword+"%");
        List<HouseEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Long countHouse() {
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
    public String delHouse(int houseid) {
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
    public String addHouse(HouseEntity houseEntity) {
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

    @Override
    public String updateHouse(HouseEntity house) {
        String back="success";
        Session session=factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            HouseEntity houseEntity=session.get(HouseEntity.class,house.getHouseId());
            if (house.getHousePassword()!=null)
                houseEntity.setHousePassword(house.getHousePassword());
            houseEntity.setDoor(house.getDoor());
            houseEntity.setUser(house.getUser());
            session.update(houseEntity);
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
    public HouseEntity getHouse(int houseid) {
        Session session=factory.openSession();
        HouseEntity houseEntity=null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            houseEntity=session.get(HouseEntity.class,houseid);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return houseEntity;
    }
}
