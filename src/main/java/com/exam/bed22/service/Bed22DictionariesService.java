package com.exam.bed22.service;

import com.exam.bed22.dto.TranslationWordDto;

public interface Bed22DictionariesService {

    TranslationWordDto translateWord(String sourceLang, String targetLang, String word);
}
