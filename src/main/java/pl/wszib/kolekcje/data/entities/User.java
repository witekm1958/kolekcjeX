package pl.wszib.kolekcje.data.entities;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "profiles")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    //    @NonNull
    @Column(name = "user_profile")
    private String userProfile;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "address_email")
    private String addressEmail;

    @Column(name = "date_registration")
    private Date dateOfRegistration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    Set<Role> roles;

//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    private List<ExhibitEntity> entities;

    public User() {
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public static User of(String userProfile, String username, String password, String addressEmail, Date dateOfRegistration) {
        User user = new User();
        user.setUserProfile(userProfile);
        user.setUsername(username);
        user.setPassword(password);
        user.setAddressEmail(addressEmail);
        user.setDateOfRegistration(dateOfRegistration);
        user.roles = new HashSet<Role>();
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toSet());
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    @Override
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

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
