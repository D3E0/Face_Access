package service;

import dao.DoorDaoInterface;
import dao.RecordDaoInterface;
import entity.DoorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorMangeServiceImp implements DoorMangeServiceInterface {

    private DoorDaoInterface doorDao;
    private RecordDaoInterface recordDao;

    @Autowired
    public void setDoorDao(DoorDaoInterface doorDao) {
        this.doorDao = doorDao;
    }
    @Autowired
    public void setRecordDao(RecordDaoInterface recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public DoorEntity getDoorEntity(int doorID) {
        return doorDao.findDoor(doorID);
    }

    @Override
    public List<DoorEntity> getDoorList(int page,int limit) {
        return doorDao.getdoorList(page,limit);
    }

    @Override
    public String update(DoorEntity doorEntity) {
        return doorDao.updateDoor(doorEntity);
    }

    public String delete(int  id){
        return doorDao.deleteDoor(id);
    }

    @Override
    public String adddoor(DoorEntity doorEntity) {
        return doorDao.addDoor(doorEntity);
    }

    @Override
    public Long countdoor() {
        return doorDao.countDoor();
    }
}
