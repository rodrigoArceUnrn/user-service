package ar.edu.unrn.userservice.security.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    private String secretKey = "secretKey";
    private String tokenPrefix = "bearer";

    private String header = "Authorization";

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

}