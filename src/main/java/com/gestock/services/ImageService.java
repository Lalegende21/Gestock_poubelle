package com.gestock.services;

import com.gestock.entites.Image;
import com.gestock.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private String path = "D:\\Mes programmes\\Spring Boot\\gestion_product\\src\\main\\java\\com\\gestock\\images\\";

    //Methode pour enregistrer une image
    @Transactional
    public Image uploadImageToFolder(MultipartFile file) throws IOException {
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("Le poids de l'image ne doit pas depasser 5MB!");
        }

        String filePath = path + file.getOriginalFilename();


        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filepath(filePath)
                .build();

        Optional<Image> optionalImage = this.imageRepository.findByName(image.getName());
        if (optionalImage.isPresent()) {
            this.imageRepository.deleteImageByName(image.getName());
        }

        Image fileData = imageRepository.save(image);

        file.transferTo(new File(filePath));

        if (filePath != null) {
            System.out.println("Image enregistre avec succes !");
        }

        return image;
    }


    //Methode pour lire une image dans la bd
    public byte[] donwloadImageFromFolder(String filename) throws IOException {
        Optional<Image> optionalImage = imageRepository.findByName(filename);
            String filePath = optionalImage.get().getFilepath();
            byte[] image = Files.readAllBytes(new File(filePath).toPath());
            return image;
    }
}
