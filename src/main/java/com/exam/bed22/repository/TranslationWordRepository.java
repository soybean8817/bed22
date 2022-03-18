package com.exam.bed22.repository;

import com.exam.bed22.domain.TranslationWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface TranslationWordRepository extends MongoRepository<TranslationWord, String> {
    Optional<TranslationWord> findFirstBySourceLangAndTargetLangAndWord(String sourceLang, String targetLang, String word);
}
