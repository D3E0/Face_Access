package service;

import dao.DoorDao;
import dao.RecordDao;
import dao.UserDaoImp;
import entity.OpenRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordMangeServiceImp implements RecordMangeService {

    DoorDao doorDao;
    RecordDao recordDao;
    UserDaoImp userDao;
    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }
    @Autowired
    public void setDoorDao(DoorDao doorDao) {
        this.doorDao = doorDao;
    }
    @Autowired
    public void setUserDao(UserDaoImp userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<OpenRecordEntity> getRecordlist(int page, int limit) {
        List<OpenRecordEntity> list=recordDao.getRecordList(page,limit);
        return list;
    }

    @Override
    public Long countRecord() {
        return recordDao.countRecord();
    }

    @Override
    public List<OpenRecordEntity> getRecordListForSearch(int page, int limit, String keyword) {
        return recordDao.getRecordListForSearch(page,limit,keyword);
    }

}
