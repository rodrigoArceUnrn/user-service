package ar.edu.unrn.userservice.domain.security.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * JwtConfig.
 */
@Configuration
public class JwtConfig {

  @Value("${jwt.secret_key}")
  private String secretKey;

  @Value("${jwt.token-prefix}")
  private String tokenPrefix;

  @Value("${jwt.header}")
  private String header;

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