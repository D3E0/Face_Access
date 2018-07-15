package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "house", schema = "my_data_base", catalog = "")
public class HouseEntity implements Serializable {
    private int houseId;
    private String housePassword;
    private UserEntity user;
    private DoorEntity door;
    public HouseEntity(){}
    public HouseEntity(int houseId,String housePassword,String doorlocation,String username){
        UserEntity userEntity=new UserEntity();
        DoorEntity doorEntity=new DoorEntity();
        userEntity.setUserName(username);
        doorEntity.setDoorLocation(doorlocation);
        this.door=doorEntity;
        this.houseId=houseId;
        this.housePassword=housePassword;
        this.user=userEntity;
    }
    @JoinColumn(name = "userID")
    @ManyToOne()
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name = "doorId")
    public DoorEntity getDoor() {
        return door;
    }

    public void setDoor(DoorEntity door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "HouseEntity{" +
                "houseId=" + houseId +
                ", housePassword='" + housePassword + '\'' +
                '}';
    }

    @Id
    @Column(name = "houseID")
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "housePassword")
    public String getHousePassword() {
        return housePassword;
    }

    public void setHousePassword(String housePassword) {
        this.housePassword = housePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HouseEntity that = (HouseEntity) o;

        if (houseId != that.houseId) {
            return false;
        }
        if (housePassword != null ? !housePassword.equals(that.housePassword) : that.housePassword != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId;
        result = 31 * result + (housePassword != null ? housePassword.hashCode() : 0);
        return result;
    }
}
