package com.biraj.ecomerceappapi.services;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CloudnaryImageServiceImp implements  CloudnaryImageService{

    private final Cloudinary cloudinary;

    @Override
    public Map upload(MultipartFile file) {

        try {
          Map data =   this.cloudinary.uploader().upload(file.getBytes(),Map.of(

            ));
          return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
