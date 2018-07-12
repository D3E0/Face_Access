package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "door", schema = "my_data_base", catalog = "")
public class DoorEntity implements Serializable {
    private int doorId;
    private String doorLocation;
    private String doorIp;
    private String doorStatus;
    public DoorEntity(){}
    public DoorEntity(String doorLocation){
        this.doorLocation=doorLocation;
    }

    @Override
    public String toString() {
        return "DoorEntity{" +
                "doorId=" + doorId +
                ", doorLocation='" + doorLocation + '\'' +
                ", doorIp='" + doorIp + '\'' +
                ", doorStasue='" + doorStatus + '\'' +
                '}';
    }

    @Id
    @Column(name = "doorID")
    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    @Basic
    @Column(name = "doorLocation")
    public String getDoorLocation() {
        return doorLocation;
    }

    public void setDoorLocation(String doorLocation) {
        this.doorLocation = doorLocation;
    }

    @Basic
    @Column(name = "doorIP")
    public String getDoorIp() {
        return doorIp;
    }

    public void setDoorIp(String doorIp) {
        this.doorIp = doorIp;
    }

    @Basic
    @Column(name = "doorStatus")
    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoorEntity that = (DoorEntity) o;

        if (doorId != that.doorId) return false;
        if (doorLocation != null ? !doorLocation.equals(that.doorLocation) : that.doorLocation != null) return false;
        if (doorIp != null ? !doorIp.equals(that.doorIp) : that.doorIp != null) return false;
        if (doorStatus != null ? !doorStatus.equals(that.doorStatus) : that.doorStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doorId;
        result = 31 * result + (doorLocation != null ? doorLocation.hashCode() : 0);
        result = 31 * result + (doorIp != null ? doorIp.hashCode() : 0);
        result = 31 * result + (doorStatus != null ? doorStatus.hashCode() : 0);
        return result;
    }
}
