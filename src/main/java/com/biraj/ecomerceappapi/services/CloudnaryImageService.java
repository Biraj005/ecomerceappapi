package com.biraj.ecomerceappapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudnaryImageService {
    public  Map upload(MultipartFile file);
}
