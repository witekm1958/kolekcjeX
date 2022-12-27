package pl.wszib.kolekcje.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wszib.kolekcje.data.entities.ImageGallery;
import pl.wszib.kolekcje.data.repositories.ImageGalleryRepository;
import pl.wszib.kolekcje.services.ImageGalleryService;
import pl.wszib.kolekcje.web.models.ImageGalleryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
public class ImageGalleryController {

    public static final String INVALID_PATH_SEQUENCE_FILE_NAME = "Uwaga! Nazwa pliku zawiera nieprawidłową sekwencję ścieżek ";
    public static final String ARTIFACT_HAS_BEEN_SAVED_TO_A_FILE = "Artefakt został zapisany w pliku - ";

//    @Autowired
    private final ImageGalleryService imageGalleryService;
    private final ImageGalleryRepository imageGalleryRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ImageGalleryController(ImageGalleryService imageGalleryService, ImageGalleryRepository imageGalleryRepository) {
        this.imageGalleryService = imageGalleryService;
        this.imageGalleryRepository = imageGalleryRepository;
    }

    @GetMapping(value = {"add_image"})
    public String addArtefactPage(
            Model model,
            @ModelAttribute("imagegallery") ImageGalleryModel imageGalleryModel) {
        return "addimage";
    }

//    @PostMapping("/image/saveImageDetails")
//    @PostMapping("/saveImage")
    @PostMapping("add_image")
    public String saveArtefact(
            @ModelAttribute("imagegallery") @Valid ImageGalleryModel imageGalleryModel,
            @RequestParam("image") MultipartFile multipartFile,
            BindingResult bindingResult,
            Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addimage";
        }

        imageGalleryModel.setImage(multipartFile.getBytes());
        imageGalleryService.saveImage(imageGalleryModel);
        return "redirect:/addimage";
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ImageGallery> imageGallery)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        imageGallery = imageGalleryService.getImageById(id);
//        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.setContentType("image/*");
        response.getOutputStream().write(imageGallery.get().getImage());
        response.getOutputStream().close();
    }

    @GetMapping("/image/imageDetails")
    String showArtefactDetails(@RequestParam("id") Long id, Optional<ImageGallery> imageGallery, Model model) {
        try {
            log.info("Id :: " + id);
            if (id != 0) {
                imageGallery = imageGalleryService.getImageById(id);

                log.info("products :: " + imageGallery);
                if (imageGallery.isPresent()) {
                    model.addAttribute("id", imageGallery.get().getId());
                    model.addAttribute("description", imageGallery.get().getDescription());
                    model.addAttribute("name", imageGallery.get().getName());
                    model.addAttribute("price", imageGallery.get().getPrice());
                    return "imagedetails";
                }
                return "redirect:/home";
            }
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home";
        }
    }

    @GetMapping("/image/show")
    String show(Model map) {
        List<ImageGallery> images = imageGalleryService.getAllActiveImages();
        map.addAttribute("images", images);
        return "images";
    }
}
