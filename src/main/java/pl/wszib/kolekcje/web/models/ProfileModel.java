package pl.wszib.kolekcje.web.models;

//import javax.validation.constraints.Size;
import javax.validation.constraints.*;

public class ProfileModel {
    @NotNull
    @Size(min = 5, max = 15)
    private String userName;
    @NotNull
    @Size(min = 5, max = 15)
    private String loginName;
    @NotNull
    @Size(min = 5, max = 30)
    private String password;
    @NotNull
    private String addressEmail;
    private String dateOfRegistration;
    private String status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
