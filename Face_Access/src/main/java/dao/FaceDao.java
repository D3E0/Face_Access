package dao;

public interface FaceDao {
    public String addface(String userid, String img);
    public String delface(String userid);
    public String updateface(String userid, String img);
}
