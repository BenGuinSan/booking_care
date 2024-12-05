package com.penguinsan.BookingCare.Service;


import com.cloudinary.Cloudinary;
import com.penguinsan.BookingCare.DTO.CloudinaryResponse;
import com.penguinsan.BookingCare.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    public CloudinaryResponse uploadFile(MultipartFile file, String folder){
        try {
            Map<String, String> options = new HashMap<>();
            options.put("folder", folder);

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

            CloudinaryResponse response = new CloudinaryResponse();
            response.setPublicId((String) uploadResult.get("public_id"));
            response.setUrl((String) uploadResult.get("secure_url"));

            return response;
        } catch (Exception e){
            throw new FileUploadUtils.FuncErrorException("Failed to upload file");
        }
    }
}
