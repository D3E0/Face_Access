package service;

public interface SignInService {

    public boolean verifyUsername(String username);

    public boolean verifyUser(String username, String password);

    public String getUsername(int userId);

    public int getUserId(String username);

    public int addUser(String username, String userTel, String password);
}
