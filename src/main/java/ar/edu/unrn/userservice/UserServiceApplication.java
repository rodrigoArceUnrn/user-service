package ar.edu.unrn.userservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * UserServiceApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = "ar.edu.unrn")
public class UserServiceApplication {

  public UserServiceApplication() {
  }

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}