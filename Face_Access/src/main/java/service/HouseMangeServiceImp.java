package service;

import dao.HouseDao;
import entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HouseMangeServiceImp implements HouseMangeService{
    private HouseDao houseDao;
    @Autowired
    public void setHouseDao(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Override
    @Cacheable(value = "houseList")
    public List<HouseEntity> getHouselist(int page, int limit) {
        return houseDao.getHouseList(page,limit);
    }
    @Override
    @Cacheable(value = "houseList")
    public List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword) {
        return houseDao.getHouseListForSearch(page,limit,keyword);
    }

    @Override
    @Cacheable(value = "houseCount")
    public Long countHouses() {
        return houseDao.countHouse();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"houseList","houseCount"},allEntries = true),
            @CacheEvict(value = "house",key = "'houseid'+#args[0]")
    })
    public String delHouse(int houseid) {
        return houseDao.delHouse(houseid);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"houseList","houseCount"},allEntries = true),
    },put = {@CachePut(value = "house",key = "'houseid'+#args[0].getHouseId()")})
    public String addHouse(HouseEntity houseEntity) {
        return houseDao.addHouse(houseEntity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = {"houseList","houseCount"},allEntries = true),
    },put = {@CachePut(value = "house",key = "'houseid'+#args[0]")})
    public String updateHousepwd(HouseEntity house) {
        return houseDao.updateHouse( house);
    }

    @Override
    @Cacheable(value = "house",key = "'houseid'+#args[0]")
    public HouseEntity getHouse(int houseid) {
        return houseDao.getHouse(houseid);
    }
}
