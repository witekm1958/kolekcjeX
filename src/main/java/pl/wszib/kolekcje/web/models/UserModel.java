package pl.wszib.kolekcje.web.models;

import pl.wszib.kolekcje.data.entities.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class UserModel {
    @NotNull
    @Size(min = 5, max = 15)
    private String userProfile;
    @NotNull
    @Size(min = 5, max = 15)
    private String username;
    @NotNull
    @Size(min = 5, max = 30)
    private String password;

    @NotNull
    @Size(min = 5, max = 30)
    private String rePassword;

    @NotNull
    @Email
    private String addressEmail;

    Set<Role> roles;



    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isItTheSamePassword() {
        return password.equals(rePassword);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel that = (UserModel) o;
        return Objects.equals(password, that.password) && Objects.equals(rePassword, that.rePassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, rePassword);
    }
}