package dao;

import entity.HouseEntity;

import java.util.List;

public interface HouseDao {

    List<Integer> getHouseIdByOwner(int ownerId);
    List<HouseEntity> getHouseList(int page, int limit);
    List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword);
    Long counthouse();
    String delhouse(int houseid);
    String addhouse(HouseEntity houseEntity);
    String updatehousepwd(int houseid,String oldpwd,String newpwd);
    HouseEntity getHouse(int houseid);
}
