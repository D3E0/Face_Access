package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "my_data_base", catalog = "")
public class UserEntity implements Serializable {
    private String userName;
    private int userId;
    private String userTelephone;
    private String userPassword;
    private int userType;

    public UserEntity() {
    }

    public UserEntity(String userName, int userId, String userTelephone) {
        this.userName = userName;
        this.userId = userId;
        this.userTelephone = userTelephone;
    }

    public UserEntity(String username) {
        userName = username;
    }


    @Basic
    @Column(name = "userType")
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "userPassword")
    public String getUserPassword() {
        return userPassword;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userTelephone")
    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", userTelephone='" + userTelephone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userTelephone != null ? !userTelephone.equals(that.userTelephone) : that.userTelephone != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (userTelephone != null ? userTelephone.hashCode() : 0);
        return result;
    }
}
