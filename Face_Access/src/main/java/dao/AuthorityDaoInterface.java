package dao;

import entity.AuthorityEntity;

import java.sql.Date;
import java.util.List;

/**
 * @author ACM-PC
 */
public interface AuthorityDaoInterface {
    int addAuthority(AuthorityEntity authorityEntity);

    void deleteAuthority(int id);

    void updateAuthority(int authorityID, Date endDate);

    AuthorityEntity getAuthority(int userID, int houseID);

    List getAuthoritiesOfUser(int userID);

    List<AuthorityEntity> getAuthoritiesOfHouse(int houseID);

    List getAuthorities();

}
