package com.gestock.controlleurs;

import com.gestock.entites.Image;
import com.gestock.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "image", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
public class ImageController {

    private ImageService imageService;

//    @PostMapping(path = "save")
//    public String saveImage(@RequestParam("image") MultipartFile file) throws IOException {
//        String filePath = this.imageService.uploadImageToFolder(file);
//        return filePath;
//    }

    @GetMapping(path = "download/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename) {
        try {
            byte[] image = imageService.donwloadImageFromFolder(filename);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(image);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed!");
        }
    }
}
