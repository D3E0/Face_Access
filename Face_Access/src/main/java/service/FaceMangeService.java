package service;

public interface FaceMangeService {
    String addface(String userid, String img);
    String delface(String userid);
    String updateface(String userid, String img);
    String searchface(String img);
}
