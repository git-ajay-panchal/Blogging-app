package com.apgroup.blogapp.services.impl;

import com.apgroup.blogapp.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${project.image}")
    String path; // folder location

    //get the file from request as Form data/Multipart and uploading it in the path.
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        // get file name
        String fileName = file.getOriginalFilename();

        //Setting up the path of the file
        // images/abc.png
//        String filePath = path + File.separator + fileName;
        // to differentiate name - same file can be uploaded with same name muli. times
        String randomID = UUID.randomUUID().toString();
        String filePath = path + File.separator + randomID + fileName.substring(fileName.lastIndexOf("."));


        // create folder if not created
        File file1 =  new File(path); //folder name
        if(!file1.exists())
            file1.mkdir();

        // copy the file
        Files.copy(file.getInputStream() , Paths.get(filePath));
        return fileName;
    }

    // To
    @Override
    public InputStream getResource(String fileName) throws FileNotFoundException {
        String fullpath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullpath);
        return is;
    }
}
