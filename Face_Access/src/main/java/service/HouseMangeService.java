package service;

import entity.HouseEntity;

import java.util.List;

public interface HouseMangeService {
    public List<HouseEntity> gethouselist(int page, int limit);
    public Long counthouses();
    public String delhouse(int houseid);
    public String addhouse(HouseEntity houseEntity);
}
