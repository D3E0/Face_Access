package service;

import entity.HouseEntity;

import java.util.List;

public interface HouseMangeService {
     List<HouseEntity> gethouselist(int page, int limit);
     Long counthouses();
     String delhouse(int houseid);
     String addhouse(HouseEntity houseEntity);
     String updatehouse(HouseEntity houseEntity);
}
