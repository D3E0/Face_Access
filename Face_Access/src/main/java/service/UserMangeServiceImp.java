package service;


import dao.AuthorityDaoInterface;
import dao.UserDaoInterface;
import entity.AuthorityEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserMangeServiceImp implements UserMangeServiceInterface {

    private AuthorityDaoInterface authorityDao;
    private UserDaoInterface userDao;

    @Autowired
    public void setAuthorityDao(AuthorityDaoInterface authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Autowired
    public void setUserDao(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<AuthorityEntity> getAuthoritiesOfHouse(int houseID) {
        return authorityDao.getAuthoritiesOfHouse(houseID);
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
    public UserEntity getUserEntity(int userID) {
        return userDao.findUser(userID);
    }

    @Override
    public List<UserEntity> getUserList() {
        return userDao.getUserList();
    }


}
