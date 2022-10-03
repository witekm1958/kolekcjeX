package pl.wszib.kolekcje.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody            // 1
    public String handleFile(@RequestPart(name = "fileupload") MultipartFile file) { // 2
        File uploadDirectory = new File("uploads");
        uploadDirectory.mkdirs();    // 3

        File oFile = new File("uploads/" + file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(oFile);
             InputStream inputStream = file.getInputStream()) {

            IOUtils.copy(inputStream, os); // 4
        } catch (IOException e) {
            e.printStackTrace();
            return "Wystąpił‚ błąd podczas przesyłania pliku: " + e.getMessage();
        }

        return "ok!";

//        return "ok!";
    }
}
