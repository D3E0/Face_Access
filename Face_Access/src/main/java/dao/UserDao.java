package dao;

import entity.UserEntity;

import java.util.List;


public interface UserDao {

    int addUser(UserEntity userEntity);

    void deleteUser(int userID);

    void updateUser(UserEntity user);

    Long checkUser(String username);

    UserEntity verifyUser(String username, String password);

    UserEntity getUserById(int userID);

    UserEntity getUserByName(String username);

    UserEntity getUserByTelephone(String telephone);

    List getUsernameList();
}
