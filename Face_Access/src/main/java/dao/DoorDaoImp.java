package dao;

import entity.DoorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DynamicUpdate
public class DoorDaoImp implements DoorDao {

    private SessionFactory factory;
    private CacheManager cacheManager;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public String addDoor(DoorEntity doorEntity) {
        String back="success";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(doorEntity);
            tx.commit();
        } catch (Exception e) {
            back="fail";
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return back;
    }


    @Override
    public String deleteDoor(int doorID) {
        String back="success";
        Session session=factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DoorEntity doorEntity=session.get(DoorEntity.class,doorID);
            session.delete(doorEntity);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
            back="fail";
        }finally {
            session.close();
        }
        return back;
    }

    @Override
    public String updateDoor(DoorEntity door) {
        String back="success";
        Session session=factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DoorEntity doorEntity=session.get(DoorEntity.class,door.getDoorId());
            if (door.getDoorIp()!=null){
                doorEntity.setDoorIp(door.getDoorIp());
            }
            if (door.getDoorLocation()!=null){
                doorEntity.setDoorLocation(door.getDoorLocation());
            }
            if (door.getDoorStatus()!=null){
                doorEntity.setDoorStatus(door.getDoorStatus());
            }
            session.update(doorEntity);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
            back="fail";
        }finally {
            session.close();
        }
        return back;
    }

    @Override
    public DoorEntity findDoor(int doorID) {
        Session session=factory.openSession();
        Transaction tx = null;
        DoorEntity doorEntity=null;
        try {
            tx = session.beginTransaction();
            doorEntity=session.get(DoorEntity.class,doorID);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return doorEntity;
    }

    @Override
    public List<DoorEntity> getDoorList(int page, int limit) {
        System.out.println("==================");
        int start=(page-1)*limit;
        int count=limit;
        Session session=factory.openSession();
        Transaction tx = null;
        List<DoorEntity> doorList=null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from DoorEntity");
            query.setFirstResult(start);
            query.setMaxResults(limit);
            doorList=query.list();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return doorList;
    }

    @Override
    public List<DoorEntity> getDoorListForSearch(int page, int limit,String keyword) {
        int start=(page-1)*limit;
        System.out.println(page);
        System.out.println(limit);
        int count=limit;
        Session session=factory.openSession();
        Transaction tx = null;
        List<DoorEntity> doorList=null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from DoorEntity where doorLocation like :A");
            query.setParameter("A","%"+keyword+"%");
            query.setFirstResult(start);
            query.setMaxResults(limit);
            doorList=query.list();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return doorList;
    }
    @Override
    public Long countDoor() {
        Session session=factory.openSession();
        Transaction tx = null;
        Long count=null;
        try {
            tx = session.beginTransaction();
            Query q   = session.createQuery("select count(doorId) from DoorEntity");
            count=(Long)q.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return count;
    }

}
