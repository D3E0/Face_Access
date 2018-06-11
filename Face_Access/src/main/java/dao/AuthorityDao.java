package dao;

import dto.AuthorityDTO;
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

    List getAuthoritiesOfHouse(int houseID);

    AuthorityDTO getAuthoritiesOfOwnerLimit(int ownerID, int start, int offset);

    List searchAuthoritiesOfHouse(int houseID, String data);

    AuthorityDTO searchAuthoritiesOfOwnerLimit(int ownerID, String data, int start, int offset);

    List getAuthorities();

    Long getCountOfAuthoritiesByOwner(int ownerID);

}
