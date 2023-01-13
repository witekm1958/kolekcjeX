package pl.wszib.kolekcje.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import pl.wszib.kolekcje.data.entities.ImageGallery;
import pl.wszib.kolekcje.data.repositories.ImageGalleryRepository;
import pl.wszib.kolekcje.web.models.ImageGalleryModel;

public class ImageGalleryMapper {

    private ImageGalleryRepository imageGalleryRepository;


    @Autowired
    public ImageGalleryMapper(ImageGalleryRepository imageGalleryRepository) {
        this.imageGalleryRepository = imageGalleryRepository;
    }

    public static ImageGallery toEntity(ImageGalleryModel model) {
        ImageGallery entity = new ImageGallery();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setImage(model.getZdjecie());
        entity.setPrice(model.getPrice());
        return entity;
    }

}