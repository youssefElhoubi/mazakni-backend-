package com.mazaknib.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MusicRequestDTO {
    @NotBlank
    @NotEmpty(message = "name should not be empty ")
    private String name;
    @NotEmpty(message = "description should not be empty ")
    private String description;

    // Input files from the form-data
    @NotNull(message = "image should not be empty ")
    private MultipartFile imageFile;
    @NotNull(message = "audio should not be empty ")
    private MultipartFile audioFile;
}
