package service;

import dao.DoorDao;
import dao.RecordDao;
import entity.DoorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "door",key = "#args[0]")
    public DoorEntity getDoorEntity(int doorID) {
        return doorDao.findDoor(doorID);
    }

    @Override
    @Cacheable(value = "doorList")
    public List<DoorEntity> getDoorList(int page, int limit) {
        return doorDao.getDoorList(page, limit);
    }

    @Override
    @Cacheable(value = "doorList")
    public List<DoorEntity> getDoorListForSearch(int page, int limit, String keyword) {
        return doorDao.getDoorListForSearch(page, limit, keyword);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"doorList","doorCount"},allEntries = true),
    },put={ @CachePut(value = "door",key = "'doorid'+#args[0].getDoorId()")})
    public String updateDoor(DoorEntity doorEntity) {
        return doorDao.updateDoor(doorEntity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"doorList","doorCount"},allEntries = true),
            @CacheEvict(value = "door",key = "'doorid'+#args[0].getDoorId()")
    })
    public String deleteDoor(int id) {
        return doorDao.deleteDoor(id);
    }

    @Override
    @Cacheable(value = "doorCount")
    public Long countDoor() {
        return doorDao.countDoor();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"doorList","doorCount"},allEntries = true),
    },put={ @CachePut(value = "door",key = "'doorid'+#args[0].get")})
    public String addDoor(DoorEntity doorEntity) {
        return doorDao.addDoor(doorEntity);
    }
}
