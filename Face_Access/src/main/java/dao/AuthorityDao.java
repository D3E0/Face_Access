package dao;

import dto.AuthorityListDTO;
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

    List getAuthoritiesOfHouse(int houseID);

    AuthorityListDTO getAuthoritiesOfUserLimit(int userID, int start, int offset);

    AuthorityListDTO searchAuthoritiesOfUserLimit(int userID, String data, int start, int offset);

    AuthorityListDTO getAuthoritiesOfOwnerLimit(int ownerID, int start, int offset);

    AuthorityListDTO searchAuthoritiesOfOwnerLimit(int ownerID, String data, int start, int offset);

    List getAuthorities();

}
