package service;

import dao.DoorDao;
import dao.RecordDao;
import entity.DoorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorMangeServiceImp implements DoorMangeService {

    private DoorDao doorDao;
    private RecordDao recordDao;

    @Autowired
    public void setDoorDao(DoorDao doorDao) {
        this.doorDao = doorDao;
    }

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public DoorEntity getDoorEntity(int doorID) {
        return doorDao.findDoor(doorID);
    }

    @Override
    public List<DoorEntity> getDoorList(int page, int limit) {
        return doorDao.getDoorList(page, limit);
    }

    @Override
    public List<DoorEntity> getDoorListForSearch(int page, int limit, String keyword) {
        return doorDao.getDoorListForSearch(page, limit, keyword);
    }

    @Override
    public String update(DoorEntity doorEntity) {
        return doorDao.updateDoor(doorEntity);
    }

    public String delete(int id) {
        return doorDao.deleteDoor(id);
    }

    @Override
    public Long countdoor() {
        return doorDao.countDoor();
    }

    @Override
    public String adddoor(DoorEntity doorEntity) {
        return doorDao.addDoor(doorEntity);
    }
}
