package pl.wszib.kolekcje.web.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ImageGalleryModel {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    @Min(0)
    private double price;
//    @NotEmpty
    private byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
