package com.gestock.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl  implements  FileService{
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {


        // obtenir le nom du fichier
        String fileName = file.getOriginalFilename();

        //  pour obtenir le chemin du fichier
        String filePath = path + File.separator + fileName;

        // créer un objet dossier
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        // Copiez le fichier ou téléchargez le fichier sur le chemin
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);



        return fileName;
    }

    @Override
    public InputStream getRessourceFile(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;

        return  new FileInputStream(filePath);


    }
}
