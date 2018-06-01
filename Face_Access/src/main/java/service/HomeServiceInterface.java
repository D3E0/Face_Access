package service;


public interface HomeServiceInterface {
    public boolean validateUser(int userId, String password);

    public boolean validateUsername(String username);

}
