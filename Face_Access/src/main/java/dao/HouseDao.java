package dao;

import entity.HouseEntity;

import java.util.List;

public interface HouseDao {

    List<Integer> getHouseIdByOwner(int ownerId);
    List<HouseEntity> getHouseList(int page, int limit);
    List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword);
    Long countHouse();
    String delHouse(int houseid);
    String addHouse(HouseEntity houseEntity);
    String updateHouse(HouseEntity houseEntity);
    HouseEntity getHouse(int houseid);
}
