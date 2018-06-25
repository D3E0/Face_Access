package service;

import entity.DoorEntity;

import java.util.List;

public interface DoorMangeService {
     DoorEntity getDoorEntity(int doorID);

     List<DoorEntity> getDoorList(int page,int limit);
     List<DoorEntity> getDoorListForSearch(int page, int limit,String keyword);
     String update(DoorEntity doorEntity);
     String delete(int id);
     String adddoor(DoorEntity doorEntity);
     Long countdoor();
}
