package com.apgroup.blogapp.services;

import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public interface FileService {
    public String uploadImage(MultipartFile file) throws IOException;
    public InputStream getResource(String fileName) throws FileNotFoundException;
}
