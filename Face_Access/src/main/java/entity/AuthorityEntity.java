package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


/**
 * @author ACM-PC
 * Entity 数据库中的每一张表对应这一个 Entity
 */
@Entity
@Table(name = "authority", schema = "my_data_base", catalog = "")
public class AuthorityEntity {
    private int authorityId;
    private Date startDate;
    private Date endDate;
    private UserEntity user;
    private HouseEntity house;
    private String remark;

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AuthorityEntity() {
    }

    public AuthorityEntity(int userID, int houseID) {
        user = new UserEntity();
        user.setUserId(userID);
        house = new HouseEntity();
        house.setHouseId(houseID);
    }


    @ManyToOne
    @JoinColumn(name = "userID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "houseID")
    public HouseEntity getHouse() {
        return house;
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "authorityId=" + authorityId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", house=" + house +
                ", remark='" + remark + '\'' +
                '}';
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    @Id
    @Column(name = "authorityID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorityEntity that = (AuthorityEntity) o;

        if (authorityId != that.authorityId) {
            return false;
        }
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) {
            return false;
        }
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
