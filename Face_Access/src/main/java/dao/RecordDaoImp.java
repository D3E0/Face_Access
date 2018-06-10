package dao;

import entity.OpenRecordEntity;
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
public class RecordDaoImp implements RecordDao {

    private SessionFactory factory;
    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public int addRecord(OpenRecordEntity recordEntity) {
        return 0;
    }

    @Override
    public String deleteRecord(int openid) {
        String back="success";
        Session session=factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            OpenRecordEntity recordEntity=session.get(OpenRecordEntity.class,openid);
            session.delete(recordEntity);
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
    public String updateRecord(OpenRecordEntity recordEntity) {
        return null;
    }

    @Override
    public OpenRecordEntity findRecord(int openid) {
        return null;
    }

    @Override
    public List<OpenRecordEntity> getRecordList(int page, int limit) {
        int start=(page-1)*limit+1;
        int count=limit;
        Session session=factory.openSession();
        Transaction tx = null;
        List<OpenRecordEntity> recordList=null;
        try {
            tx = session.beginTransaction();
            recordList = session.createQuery("from OpenRecordEntity where openId between "+start+" and "+(start+count-1)).list();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return recordList;
    }

    @Override
    public Long countRecord() {
        Session session=factory.openSession();
        Transaction tx = null;
        Long count = null;
        try {
            tx = session.beginTransaction();
            Query q  =session.createQuery("select count(*) from OpenRecordEntity");
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

}
