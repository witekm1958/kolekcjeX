package pl.wszib.kolekcje.data.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long idUser;

    @NonNull
    @Column(name = "user_name")
    private String userName;

    @NonNull
    @Column(name = "login_name")
    private String loginName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NonNull
    @NotEmpty
//    @Email
    @Column(name = "address_email")
    private String addressEmail;
    @Column(name = "date_registration")
    private Date dateOfRegistration;

    @NonNull
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "profileEntity", cascade = {CascadeType.ALL})
    private List<ExhibitEntity> entities;

    public ProfileEntity() {
    }

    public ProfileEntity(String userName, String password, String loginName, String addressEmail, Date dateOfRegistration, String status) {
        this.userName = userName;
        this.password = password;
        this.loginName = loginName;
        this.addressEmail = addressEmail;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ExhibitEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<ExhibitEntity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginName='" + loginName + '\'' +
                ", addressEmail='" + addressEmail + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
