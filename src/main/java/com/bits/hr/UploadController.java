package com.bits.hr;

import com.bits.hr.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    private final UploadService uploadService;
    @Autowired
    private UploadService2 uploadService2;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
   // public List<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws Exception{
    public List<String> upload(@RequestParam("file") MultipartFile file) throws Exception{
       return uploadService2.upload(file);
       // return uploadService.upload(file);

    }
}
