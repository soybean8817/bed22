package com.exam.bed22.service.impl;

import com.exam.bed22.domain.TranslationWord;
import com.exam.bed22.dto.TranslationWordDto;
import com.exam.bed22.repository.TranslationWordRepository;
import com.exam.bed22.service.Bed22DictionariesService;
import com.exam.bed22.service.oxford.OxfordDictionariesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Bed22DictionariesServiceImpl implements Bed22DictionariesService {

    private final OxfordDictionariesService oxfordDictionariesService;
    private final TranslationWordRepository translationWordRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public Bed22DictionariesServiceImpl(OxfordDictionariesService oxfordDictionariesService, TranslationWordRepository translationWordRepository, ObjectMapper objectMapper) {
        this.oxfordDictionariesService = oxfordDictionariesService;
        this.translationWordRepository = translationWordRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TranslationWordDto translateWord(String sourceLang, String targetLang, String word) {
        Optional<TranslationWord> translationWord = translationWordRepository.findFirstBySourceLangAndTargetLangAndWord(sourceLang, targetLang, word);
        if (translationWord.isPresent()) {
            return objectMapper.convertValue(translationWord, TranslationWordDto.class);
        }
        String result = oxfordDictionariesService.translationWord(sourceLang, targetLang, word);
        if (StringUtils.isNotEmpty(result)) {
            TranslationWord item = TranslationWord.builder()
                    .sourceLang(sourceLang)
                    .targetLang(targetLang)
                    .word(word)
                    .result(result).build();
            translationWordRepository.save(item);
            return objectMapper.convertValue(translationWord, TranslationWordDto.class);
        }
        return null;
    }
}
