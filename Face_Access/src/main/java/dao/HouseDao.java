package dao;

import entity.HouseEntity;

import java.util.List;

public interface HouseDao {

    List<HouseEntity> getHouses(int userId);
}
