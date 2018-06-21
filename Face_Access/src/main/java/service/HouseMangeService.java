package service;

import entity.HouseEntity;

import java.util.List;

public interface HouseMangeService {
     List<HouseEntity> gethouselist(int page, int limit);
     List<HouseEntity> getHouseListForSearch(int page, int limit,String keyword);
     Long counthouses();
     String delhouse(int houseid);
     String addhouse(HouseEntity houseEntity);
     String updatehousepwd(int houseid,String oldpwd,String newpwd);
     HouseEntity gethouse(int houseid);
}
