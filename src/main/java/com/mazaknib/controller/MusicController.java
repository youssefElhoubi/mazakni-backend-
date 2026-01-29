package com.mazaknib.controller;

import com.mazaknib.dto.MusicRequestDTO;
import com.mazaknib.dto.MusicResponseDTO;
import com.mazaknib.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/music")
@RequiredArgsConstructor
public class MusicController {
    private final MusicService musicService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicResponseDTO> createMusic(@ModelAttribute MusicRequestDTO requestDTO) throws IOException {
        MusicResponseDTO response = musicService.save(requestDTO);
        return ResponseEntity.ok(response);
    }

    // 2. Stream Media (This is how you listen to audio or view image)
    @GetMapping("/media/{fileId}")
    public ResponseEntity<InputStreamResource> streamMedia(@PathVariable String fileId) throws IOException {
        // In a real app, you might want to fetch content-type from DB or GridFS metadata
        // For simplicity, we assume generic stream or store type in metadata

        java.io.InputStream stream = musicService.getFileStream(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // Or dynamic type
                .body(new InputStreamResource(stream));
    }
}
