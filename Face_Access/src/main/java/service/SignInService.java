package service;

public interface SignInService {

    public boolean verifyUserId(int userId);

    public boolean verifyUser(int userId, String password);
}
