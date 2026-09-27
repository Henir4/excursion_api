package com.viagens.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "app.jwt")
@Getter @NoArgsConstructor 
public class JwtProperties {

    private String secret;
    
    private Long expirationMs;
}
