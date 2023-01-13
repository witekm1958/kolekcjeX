package pl.wszib.kolekcje.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wszib.kolekcje.data.entities.ImageGallery;
import pl.wszib.kolekcje.services.ImageGalleryService;
import pl.wszib.kolekcje.web.models.ImageGalleryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ImageGalleryController {

    public static final String INVALID_PATH_SEQUENCE_FILE_NAME = "Uwaga! Nazwa pliku zawiera nieprawidłową sekwencję ścieżek ";
    public static final String ARTIFACT_HAS_BEEN_SAVED_TO_A_FILE = "Artefakt został zapisany w pliku - ";

    @Value("/resources")
    private String uploadFolder;

    @Autowired
    private ImageGalleryService imageGalleryService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ImageGalleryController() {
    }

    public ImageGalleryController(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public ImageGalleryController(ImageGalleryService imageGalleryService) {
        this.imageGalleryService = imageGalleryService;
    }

    @GetMapping(value = {"/add_image"})
    public String addArtefactPage(@ModelAttribute("imageGalleryModel") ImageGalleryModel imageGalleryModel) {
        return "addimage";
    }

    //    @PostMapping("/image/saveImageDetails")
    @PostMapping("/add_image")
    public String saveArtefact(
            @ModelAttribute("imageGalleryModel") @Valid ImageGalleryModel imageGalleryModel,
            @RequestParam("image") MultipartFile file,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) throws IOException {

        if(file.getBytes().length == 0) {
            bindingResult.addError(new FieldError("imageGalleryModel", "zdjecie", "Zdjecie jest wymagane"));
            return "addimage";
        }

//        if(bindingResult.hasErrors()) {
//            model.addAttribute(imageGalleryModel);
//            model.addAttribute("org.springframework.validation.BindingResult.imageGalleryModel", bindingResult);
//            return "addimage";
//        }

        if (bindingResult.hasErrors()) {
            return "addimage";
        }

        imageGalleryModel.setZdjecie(file.getBytes());
        imageGalleryService.saveImage(imageGalleryModel);

        return "redirect:/add_image";
    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ImageGallery> imageGallery)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        imageGallery = imageGalleryService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
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
