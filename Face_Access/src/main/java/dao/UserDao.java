package dao;

import entity.UserEntity;

import java.util.List;


public interface UserDao {

    int addUser(UserEntity userEntity);

    void deleteUser(int userID);

    void updateUser(UserEntity user);

    UserEntity verifyUser(int userId, String password);

    UserEntity findUser(int userID);

    List<UserEntity> getUserList();
}
