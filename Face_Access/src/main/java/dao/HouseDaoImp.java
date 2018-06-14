package dao;

import entity.HouseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<Integer> getHouseIdByOwner(int ownerId) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select houseId from HouseEntity where user.userId = :id");
        query.setParameter("id", ownerId);
        List<Integer> list = query.list();
        session.getTransaction().commit();
        return list;
    }
}
