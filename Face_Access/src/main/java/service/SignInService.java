package service;

import entity.UserEntity;

public interface SignInService {

    public boolean verifyUsername(String username);

    public boolean verifyUser(String username, String password);

    public int verifyUserByFace(String img);

    public String getUsername(int userId);

    public String getUserType(int userId);

    public int getUserId(String username);

    public int getUserIdByTelephone(String telephone);

    public UserEntity getUser(int userId);

    public UserEntity getUserByUsername(String username);

    public int addUser(String username, String userTel, String password);
}
