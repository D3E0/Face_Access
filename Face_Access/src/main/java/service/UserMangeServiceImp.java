package service;


import dao.AuthorityDao;
import dao.HouseDao;
import dao.UserDao;
import dto.AuthorityListDTO;
import entity.AuthorityEntity;
import entity.UserEntity;
import manager.FaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.EncryptInfo;

import java.sql.Date;
import java.util.List;

@Service
public class UserMangeServiceImp implements UserMangeService {

    private AuthorityDao authorityDao;
    private UserDao userDao;
    private HouseDao houseDao;
    private FaceManager faceManager;

    @Autowired
    public void setFaceManager(FaceManager faceManager) {
        this.faceManager = faceManager;
    }

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
    public List<Integer> getHousesByOwner(int userID) {
        return houseDao.getHouseIdByOwner(userID);
    }

    @Override
    public List getAuthoritiesByHouse(int houseID) {
        return authorityDao.getAuthoritiesOfHouse(houseID);
    }

    @Override
    public AuthorityListDTO getAuthoritiesOfUserLimit(int userID, int start, int offset) {
        return authorityDao.getAuthoritiesOfUserLimit(userID, start, offset);
    }

    @Override
    public AuthorityListDTO searchAuthoritiesOfUserLimit(int userID, String additional, int start, int offset) {
        return authorityDao.searchAuthoritiesOfUserLimit(userID, additional, start, offset);
    }

    @Override
    public AuthorityListDTO getAuthoritiesByOwnerLimit(int ownerID, int start, int offset) {
        return authorityDao.getAuthoritiesOfOwnerLimit(ownerID, start, offset);
    }

    @Override
    public AuthorityListDTO searchAuthoritiesByOwnerLimit(int ownerID, String additional, int start, int offset) {
        return authorityDao.searchAuthoritiesOfOwnerLimit(ownerID, additional, start, offset);
    }

    @Override
    public void updateEndDate(int authorityID, Date endDate) {
        authorityDao.updateEndDate(authorityID, endDate);
    }

    @Override
    public int updateRemark(int authorityID, String remark) {
        return authorityDao.updateRemark(authorityID, remark);
    }

    @Override
    public int updatePassword(int userId, String password, String oldPassword) {
        UserEntity entity = userDao.getUserById(userId);
        password = EncryptInfo.MD5(password);
        oldPassword = EncryptInfo.MD5(oldPassword);
        if (entity.getUserPassword().equals(oldPassword)) {
            entity.setUserPassword(password);
            userDao.updateUser(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateTelephone(int userId, String telephone) {
        UserEntity entity = new UserEntity();
        entity.setUserTelephone(telephone);
        entity.setUserId(userId);
        userDao.updateUser(entity);
        return 0;
    }

    @Override
    public Boolean updateUserFace(int userId, String face) {
        return faceManager.updateFace(userId + "", face);
    }

    @Override
    public void addAuthority(int houseID, int userID, Date startDate, Date endDate, String remark) {
        AuthorityEntity entity = new AuthorityEntity(userID, houseID);
        entity.setEndDate(endDate);
        entity.setStartDate(startDate);
        entity.setRemark(remark);
        authorityDao.addAuthority(entity);
    }

    @Override
    public void deleteAuthority(int authorityID) {
        authorityDao.deleteAuthority(authorityID);
    }

    @Override
    public UserEntity getUserEntity(int userID) {
        return userDao.getUserById(userID);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<String> getUsernameList() {
        return userDao.getUsernameList();
    }


}
