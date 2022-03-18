package com.exam.bed22.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(OxfordDictionariesConfiguration.PREFIX)
public class OxfordDictionariesConfiguration {

    public static final String PREFIX = "oxford";

    private String apiURL;
    private String appId;
    private String appKey;

}
