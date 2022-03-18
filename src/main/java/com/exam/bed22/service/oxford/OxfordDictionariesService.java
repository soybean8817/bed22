package com.exam.bed22.service.oxford;


import com.exam.bed22.config.OxfordDictionariesConfiguration;
import com.exam.bed22.exception.Bed22Exception;
import com.exam.bed22.exception.Bed22ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OxfordDictionariesService {


    private final OxfordDictionariesConfiguration oxfordDictionariesConfiguration;
    private final RestTemplate restTemplate;

    @Autowired
    public OxfordDictionariesService(OxfordDictionariesConfiguration oxfordDictionariesConfiguration, RestTemplate restTemplate) {
        this.oxfordDictionariesConfiguration = oxfordDictionariesConfiguration;
        this.restTemplate = restTemplate;
    }

    public String translationWord(String sourceLang, String word, String targetLang) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("app_id", oxfordDictionariesConfiguration.getAppId());
        headers.set("app_key", oxfordDictionariesConfiguration.getAppKey());
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String url = oxfordDictionariesConfiguration.getApiURL() + "/translations/"+ sourceLang +"/"+ targetLang +"/"+ word;
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                return responseEntity.getBody();
            } else if (HttpStatus.NOT_FOUND.equals(responseEntity.getStatusCode())){
                throw new Bed22Exception(Bed22ExceptionType.NOT_FOUND, "no bilingual entry is found matching supplied source_lang and id and/or that entry has no senses with translations in the target language.");
            } else if (HttpStatus.URI_TOO_LONG.equals(responseEntity.getStatusCode())) {
                throw new Bed22Exception(Bed22ExceptionType.URI_TOO_LONG, "URL is too long.");
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(responseEntity.getStatusCode())) {
                throw new Bed22Exception(Bed22ExceptionType.INTERNAL_SERVER_ERROR, "Internal error. An error occurred during processing.");
            }
        } catch (RestClientException e) {
            throw new Bed22Exception( Bed22ExceptionType.FAILURE, "Error while searching Oxford API", e.getMessage());
        }
        return null;
    }
}
