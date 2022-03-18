package com.exam.bed22.controller;


import com.exam.bed22.dto.TranslationWordDto;
import com.exam.bed22.service.Bed22DictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bed22DictionariesController {

    @Autowired
    Bed22DictionariesService bed22DictionariesService;

    @GetMapping(path = "/word/translation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TranslationWordDto translationWord(@PathVariable String sourceLang, @PathVariable String targetLang, @PathVariable String word) {
        return bed22DictionariesService.translateWord(sourceLang, targetLang, word);
    }
}
