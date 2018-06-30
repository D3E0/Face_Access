package service;

import entity.UserEntity;

public interface SignInService {

    public boolean verifyUsername(String username);

    public boolean verifyUser(String username, String password);

    public Boolean verifyUserByFace(String img);

    public String getUsername(int userId);

    public int getUserId(String username);

    public UserEntity getUserByTelephone(String telephone);

    public int addUser(String username, String userTel, String password);
}
