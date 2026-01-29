package com.mazaknib.service;

import com.mazaknib.dto.MusicRequestDTO;
import com.mazaknib.dto.MusicResponseDTO;
import com.mazaknib.entity.Music;
import com.mazaknib.mapper.MusicMapper;
import com.mazaknib.repository.MusicRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MusicService{
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public MusicResponseDTO save(MusicRequestDTO req) throws IOException {
        Music music = musicMapper.toEntity(req);

        String musicImage = upload(req.getImageFile());
        String musicAudio = upload(req.getAudioFile());

        music.setImage(musicImage);
        music.setAudio(musicAudio);

        return musicMapper.toResponse(musicRepository.save(music));
    }

    private String upload(MultipartFile file) throws IOException {
        DBObject object = new BasicDBObject();
        object.put("type", file.getContentType());
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType(),
                object
        );
        return id.toString();
    }
    public Music getMusic(String id) {
        return musicRepository.findById(id).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public java.io.InputStream getFileStream(String fileId) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        return operations.getResource(gridFSFile).getInputStream();
    }
}
