package service;


import entity.AuthorityEntity;
import entity.HouseEntity;
import entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserMangeService {


    public List<HouseEntity> getHousesOfOwner(int userID);

    public List<AuthorityEntity> getAuthoritiesOfHouse(int houseID);

    public List<AuthorityEntity> getAuthoritiesOfOwner(int userID);

    public void updateAuthorityOfHouse(int authorityID, Date endDate);

    public void addAuthorityOfHouse(int houseID, int userID, Date startDate, Date endDate);

    public void deleteAuthority(int authorityID);

    public UserEntity getUserEntity(int userID);

    public List<UserEntity> getUserList();


}
