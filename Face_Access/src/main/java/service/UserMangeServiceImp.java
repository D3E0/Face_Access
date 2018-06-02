package service;


import dao.AuthorityDao;
import dao.HouseDao;
import dao.UserDao;
import entity.AuthorityEntity;
import entity.HouseEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMangeServiceImp implements UserMangeService {

    private AuthorityDao authorityDao;
    private UserDao userDao;
    private HouseDao houseDao;

    @Autowired
    public void setHouseDao(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Autowired
    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<HouseEntity> getHousesOfOwner(int userID) {
        return houseDao.getHouses(userID);
    }

    @Override
    public List<AuthorityEntity> getAuthoritiesOfHouse(int houseID) {
        return authorityDao.getAuthoritiesOfHouse(houseID);
    }

    @Override
    public List<AuthorityEntity> getAuthoritiesOfOwner(int userID) {
        List<HouseEntity> houseEntities = getHousesOfOwner(userID);
        List<AuthorityEntity> authorityEntities = new ArrayList<AuthorityEntity>();
        for (HouseEntity houseEntity : houseEntities) {
            authorityEntities.addAll(getAuthoritiesOfHouse(houseEntity.getHouseId()));
        }
        return authorityEntities;
    }

    @Override
    public void updateAuthorityOfHouse(int authorityID, Date endDate) {
        authorityDao.updateAuthority(authorityID, endDate);
    }

    @Override
    public void addAuthorityOfHouse(int houseID, int userID, Date startDate, Date endDate) {
        AuthorityEntity entity = new AuthorityEntity(userID, houseID);
        entity.setEndDate(endDate);
        entity.setStartDate(startDate);
        authorityDao.addAuthority(entity);
    }

    @Override
    public void deleteAuthority(int authorityID) {
        authorityDao.deleteAuthority(authorityID);
    }

    @Override
    public UserEntity getUserEntity(int userID) {
        return userDao.findUser(userID);
    }

    @Override
    public List<UserEntity> getUserList() {
        return userDao.getUserList();
    }


}
