package service;


import entity.HouseEntity;
import entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserMangeService {

    public List<HouseEntity> getHousesByOwner(int userID);

    public List getAuthoritiesByHouse(int houseID);

    public List getAuthoritiesByOwner(int ownerID);

    public List getAuthoritiesByOwnerLimit(int ownerID, int start, int offset);

    public List searchAuthoritiesByOwner(int userID, String additional);

    public List searchAuthoritiesByOwnerLimit(int ownerID, String additional, int start, int offset);

    public Long getCountOfAuthoritiesByOwner(int ownerID);

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
