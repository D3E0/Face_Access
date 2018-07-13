package service;

import entity.HouseEntity;

import java.util.List;

public interface HouseMangeService {
     List<HouseEntity> getHouselist(int page, int limit);
     List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword);
     Long countHouses();
     String delHouse(int houseid);
     String addHouse(HouseEntity houseEntity);
     String updateHousepwd(HouseEntity house);
     HouseEntity getHouse(int houseid);
}
