package entity;

import javax.persistence.*;

@Entity
@Table(name = "house", schema = "my_data_base", catalog = "")
public class HouseEntity {
    private int houseId;
    private String housePassword;
    private DoorEntity door;
    private UserEntity user;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseEntity that = (HouseEntity) o;

        if (houseId != that.houseId) return false;
        if (housePassword != null ? !housePassword.equals(that.housePassword) : that.housePassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId;
        result = 31 * result + (housePassword != null ? housePassword.hashCode() : 0);
        return result;
    }
}
