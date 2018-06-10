package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "open_record", schema = "my_data_base", catalog = "")
public class OpenRecordEntity {
    private int openId;
    private Integer userId;
    private Integer doorId;
    private Timestamp openDate;
    private String openResult;
    private DoorEntity doorEntity;
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "doorId")
    public DoorEntity getDoorEntity() {
        return doorEntity;
    }

    public void setDoorEntity(DoorEntity doorEntity) {
        this.doorEntity = doorEntity;
    }
    @ManyToOne
    @JoinColumn(name = "userId")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Id
    @Column(name = "openID")
    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "userID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "doorID")
    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    @Basic
    @Column(name = "openDate")
    public Timestamp getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    @Basic
    @Column(name = "openResult")
    public String getOpenResult() {
        return openResult;
    }

    public void setOpenResult(String openResult) {
        this.openResult = openResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenRecordEntity that = (OpenRecordEntity) o;

        if (openId != that.openId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (doorId != null ? !doorId.equals(that.doorId) : that.doorId != null) return false;
        if (openDate != null ? !openDate.equals(that.openDate) : that.openDate != null) return false;
        if (openResult != null ? !openResult.equals(that.openResult) : that.openResult != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = openId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (doorId != null ? doorId.hashCode() : 0);
        result = 31 * result + (openDate != null ? openDate.hashCode() : 0);
        result = 31 * result + (openResult != null ? openResult.hashCode() : 0);
        return result;
    }
}
