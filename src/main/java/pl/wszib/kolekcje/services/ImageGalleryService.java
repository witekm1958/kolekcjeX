package pl.wszib.kolekcje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.ImageGallery;
import pl.wszib.kolekcje.data.repositories.ImageGalleryRepository;
import pl.wszib.kolekcje.web.mappers.ImageGalleryMapper;
import pl.wszib.kolekcje.web.models.ImageGalleryModel;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImageGalleryService {

    private final ImageGalleryRepository imageGalleryRepository;
    Date date = new Date();

    @Autowired
    public ImageGalleryService(ImageGalleryRepository imageGalleryRepository) {
        this.imageGalleryRepository = imageGalleryRepository;
    }

    @Transactional
    public void saveImage(ImageGalleryModel imageGalleryModel) {
        ImageGallery imageGallery = ImageGalleryMapper.toEntity(imageGalleryModel);
        imageGallery.setCreateDate(date);

        imageGalleryRepository.save(imageGallery);
    }

    public List<ImageGallery> getAllActiveImages() {
        return imageGalleryRepository.findAll();
    }

    public Optional<ImageGallery> getImageById(Long id) {
        return imageGalleryRepository.findById(id);
    }

//    public void saveImage() {
//    }
}
