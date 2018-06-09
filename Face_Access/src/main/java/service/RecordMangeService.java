package service;

import dao.DoorDaoInterface;
import dao.RecordDaoInterface;
import dao.UserDaoImp;
import entity.OpenRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordMangeService implements RecordMangeServiceInterface {

    DoorDaoInterface doorDao;
    RecordDaoInterface recordDao;
    UserDaoImp userDao;
    @Autowired
    public void setRecordDao(RecordDaoInterface recordDao) {
        this.recordDao = recordDao;
    }
    @Autowired
    public void setDoorDao(DoorDaoInterface doorDao) {
        this.doorDao = doorDao;
    }
    @Autowired
    public void setUserDao(UserDaoImp userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<OpenRecordEntity> getRecordlist(int page, int limit) {
        List<OpenRecordEntity> list=recordDao.getRecordList(page,limit);
        for(OpenRecordEntity recordEntity:list){
            recordEntity.setDoorEntity(doorDao.findDoor(recordEntity.getDoorId()));
            recordEntity.setUserEntity(userDao.getUserById(recordEntity.getUserId()));
        }
        return list;
    }

    @Override
    public Long countRecord() {
        return recordDao.countRecord();
    }

}
