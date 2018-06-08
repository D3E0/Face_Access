package service;


import entity.AuthorityEntity;
import entity.HouseEntity;
import entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserMangeService {

    public List<HouseEntity> getHousesByOwner(int userID);

    public List<AuthorityEntity> getAuthoritiesByHouse(int houseID);

    public List<AuthorityEntity> getAuthoritiesByOwner(int userID);

    public void updateEndDate(int authorityID, Date endDate);

    public int updateRemark(int authorityID, String remark);

    public int updatePassword(int userId, String password, String oldPassword);

    public int updateTelephone(int userId, String telephone);

    public int updateUserFace(int userId, byte[] face);

    public void addAuthority(int houseID, int userID, Date startDate, Date endDate, String remark);

    public void deleteAuthority(int authorityID);

    public UserEntity getUserEntity(int userID);

    public UserEntity getUserByUsername(String username);

    public List<UserEntity> getUserList();

}
