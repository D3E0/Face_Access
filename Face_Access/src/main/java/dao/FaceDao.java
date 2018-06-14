package dao;

public interface FaceDao {
     String addface(String userid, String img);
     String delface(String userid);
     String updateface(String userid, String img);
}
