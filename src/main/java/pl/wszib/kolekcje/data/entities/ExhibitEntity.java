package pl.wszib.kolekcje.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "exhibits")
public class ExhibitEntity {
    @Id
    @GeneratedValue
    @Column(name = "id_exhibit")
    private Long idExhibit;
    @Column(name = "exhibit_name")          // nazwa eksponatu
    private String exhibitName;
    @Column(name = "pattern")               // wzór
    private Integer pattern;
    @Column(name = "production_year")       // rok produkcji
    private Integer productionYear;
    @Column(name = "main_photo_symbol")     // identyfikator głównego zdjęciazdjęcia
    private String mainPhotoSymbol;
    @Column(name = "type_of_weapon")        // typ broni
    private String typeOfWeapon;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private ProfileEntity profileEntity;

    public ExhibitEntity() {
    }

    public ExhibitEntity(Long idExhibit, String exhibitName, Integer pattern, Integer productionYear, String mainPhotoSymbol, String typeOfWeapon) {
        this.idExhibit = idExhibit;
        this.exhibitName = exhibitName;
        this.pattern = pattern;
        this.productionYear = productionYear;
        this.mainPhotoSymbol = mainPhotoSymbol;
        this.typeOfWeapon = typeOfWeapon;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getMainPhotoSymbol() {
        return mainPhotoSymbol;
    }

    public void setMainPhotoSymbol(String mainPhotoSymbol) {
        this.mainPhotoSymbol = mainPhotoSymbol;
    }

    public String getTypeOfWeapon() {
        return typeOfWeapon;
    }

    public void setTypeOfWeapon(String typeOfWeapon) {
        this.typeOfWeapon = typeOfWeapon;
    }

    public ProfileEntity getUser() {
        return profileEntity;
    }

    public void setUser(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    @Override
    public String toString() {
        return "ExhibitEntity{" +
                "exhibitName='" + exhibitName + '\'' +
                ", pattern=" + pattern +
                ", productionYear=" + productionYear +
                ", mainPhotoSymbol='" + mainPhotoSymbol + '\'' +
                ", typeOfWeapon='" + typeOfWeapon + '\'' +
                '}';
    }
}
