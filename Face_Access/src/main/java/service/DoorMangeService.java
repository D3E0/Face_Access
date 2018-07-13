package service;

import entity.DoorEntity;

import java.util.List;

public interface DoorMangeService {
     DoorEntity getDoorEntity(int doorID);

     List<DoorEntity> getDoorList(int page,int limit);
     List<DoorEntity> getDoorListForSearch(int page, int limit,String keyword);
     String updateDoor(DoorEntity doorEntity);
     String deleteDoor(int id);
     String addDoor(DoorEntity doorEntity);
     Long countDoor();
}
