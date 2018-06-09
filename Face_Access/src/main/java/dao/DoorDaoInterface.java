package dao;

import entity.DoorEntity;

import java.util.List;

public interface DoorDaoInterface {
    String addDoor(DoorEntity doorEntity);

    String deleteDoor(int doorID);

    String updateDoor(DoorEntity door);

    DoorEntity findDoor(int doorID);

    List<DoorEntity> getdoorList(int page,int limit);
    Long countDoor();
}
