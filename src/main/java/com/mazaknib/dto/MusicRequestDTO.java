package com.mazaknib.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MusicRequestDTO {
    private String name;
    private String description;

    // Input files from the form-data
    private MultipartFile imageFile;
    private MultipartFile audioFile;
}
