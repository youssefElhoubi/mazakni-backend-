package com.mazaknib.repository;

import com.mazaknib.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music, String> {
}