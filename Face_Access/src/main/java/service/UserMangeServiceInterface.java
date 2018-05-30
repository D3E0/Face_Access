package service;


import entity.AuthorityEntity;
import entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface UserMangeServiceInterface {


    public List<AuthorityEntity> getAuthoritiesOfHouse(int houseID);

    public void updateAuthorityOfHouse(int authorityID, Date endDate);

    public UserEntity getUserEntity(int userID);

    public List<UserEntity> getUserList();


}
