package manager;

public interface FaceManager {
    String addface(String userid, String img);

    String delface(String userid);

    Boolean updateFace(String userid, String img);

    int searchFace(String img);
}
