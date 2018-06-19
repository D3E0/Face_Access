package dto;

import java.sql.Date;

/**
 * Data Transfer Object 数据传输对象
 */
public class AuthorityDTO {
    private int houseId;
    private String userName;
    private int authorityId;
    private Date startDate;
    private Date endDate;
    private String remark;


    public AuthorityDTO(int houseId, String userName, int authorityId, Object startDate, Object endDate, String remark) {
        this.houseId = houseId;
        this.userName = userName;
        this.authorityId = authorityId;
        this.startDate = (Date) startDate;
        this.endDate = (Date) endDate;
        this.remark = remark;
    }

    public AuthorityDTO(int houseId, String userName, Object startDate, Object endDate) {
        this.houseId = houseId;
        this.userName = userName;
        this.startDate = (Date) startDate;
        this.endDate = (Date) endDate;
    }

    public AuthorityDTO() {
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
