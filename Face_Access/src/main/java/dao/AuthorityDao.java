package dao;

import entity.AuthorityEntity;

import java.sql.Date;
import java.util.List;

/**
 * @author ACM-PC
 */
public interface AuthorityDao {
    int addAuthority(AuthorityEntity authorityEntity);

    void deleteAuthority(int id);

    void updateEndDate(int authorityID, Date endDate);

    int updateRemark(int authorityID, String remark);

    AuthorityEntity getAuthority(int userID, int houseID);

    List getAuthoritiesOfUser(int userID);

    List<AuthorityEntity> getAuthoritiesOfHouse(int houseID);

    List getAuthorities();

}
