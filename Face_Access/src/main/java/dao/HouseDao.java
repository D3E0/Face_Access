package dao;

import entity.HouseEntity;

import java.util.List;

public interface HouseDao {

    List<Integer> getHouseIdByOwner(int ownerId);
    List<HouseEntity> getHouses(int page, int limit);
    Long counthouse();
    String delhouse(int houseid);
    String addhouse(HouseEntity houseEntity);
    String updatehouse(HouseEntity house);
}
