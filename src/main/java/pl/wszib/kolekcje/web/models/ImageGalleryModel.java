package pl.wszib.kolekcje.web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

public class ImageGalleryModel {

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String description;

    private double price;

    private byte[] zdjecie;

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

    public byte[] getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(byte[] zdjecie) { this.zdjecie = zdjecie; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageGalleryModel)) return false;
        ImageGalleryModel that = (ImageGalleryModel) o;
        return Double.compare(that.price, price) == 0 && name.equals(that.name) && description.equals(that.description) && Arrays.equals(zdjecie, that.zdjecie);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, description, price);
        result = 31 * result + Arrays.hashCode(zdjecie);
        return result;
    }
}
