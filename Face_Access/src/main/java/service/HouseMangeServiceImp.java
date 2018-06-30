package service;

import dao.HouseDao;
import entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class HouseMangeServiceImp implements HouseMangeService{
    private HouseDao houseDao;
    @Autowired
    public void setHouseDao(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Override
    public List<HouseEntity> gethouselist(int page, int limit) {
        return houseDao.getHouseList(page,limit);
    }
    @Override
    public List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword) {
        return houseDao.getHouseListForSearch(page,limit,keyword);
    }

    @Override
    public Long counthouses() {
        return houseDao.counthouse();
    }

    @Override
    public String delhouse(int houseid) {
        return houseDao.delhouse(houseid);
    }

    @Override
    public String addhouse(HouseEntity houseEntity) {
        return houseDao.addhouse(houseEntity);
    }

    @Override
    public String updatehousepwd(HouseEntity house) {
        return houseDao.updatehouse( house);
    }

    @Override
    public HouseEntity gethouse(int houseid) {
        return houseDao.getHouse(houseid);
    }
}
