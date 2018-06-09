package service;

import entity.DoorEntity;

import java.util.List;

public interface DoorMangeServiceInterface {
    public DoorEntity getDoorEntity(int doorID);

    public List<DoorEntity> getDoorList(int page,int limit);

    public String update(DoorEntity doorEntity);
    public String delete(int id);
    public String adddoor(DoorEntity doorEntity);
}
