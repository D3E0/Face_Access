package dao;

import entity.DoorEntity;

import java.util.List;

public interface DoorDao {
    String addDoor(DoorEntity doorEntity);

    String deleteDoor(int doorID);

    String updateDoor(DoorEntity door);

    DoorEntity findDoor(int doorID);

    List<DoorEntity> getDoorList(int page, int limit);
    List<DoorEntity> getDoorListForSearch(int page, int limit,String keyword);
    Long countDoor();
}
