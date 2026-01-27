package com.mazaknib.mapper;

import com.mazaknib.dto.MusicRequestDTO;
import com.mazaknib.dto.MusicResponseDTO;
import com.mazaknib.entity.Music;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicMapper {

    // We ignore file fields here; the Service will handle the upload and setting of IDs
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "audio", ignore = true)
    Music toEntity(MusicRequestDTO request);

    // Map the entity's stored String IDs to the Response DTO
    @Mapping(source = "image", target = "imageId")
    @Mapping(source = "audio", target = "audioId")
    MusicResponseDTO toResponse(Music music);
}