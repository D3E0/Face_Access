package dao;

import entity.UserEntity;

import java.util.List;


public interface UserDaoInterface {

    int addUser(UserEntity userEntity);

    void deleteUser(int userID);

    void updateUser(UserEntity user);

    UserEntity findUser(int userID);

    List<UserEntity> getUserList();
}
