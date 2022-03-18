package com.exam.bed22.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranslationWordDto {
    private String sourceLang;
    private String targetLang;
    private String word;
    private String result;
}
