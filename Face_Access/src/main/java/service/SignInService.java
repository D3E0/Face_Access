package service;

public interface SignInService {

    public boolean verifyUserId(int userId);

    public boolean verifyUser(int userId, String password);

    public String getUsername(int userId);

    public int addUser(String username, String userTel, String password);
}
