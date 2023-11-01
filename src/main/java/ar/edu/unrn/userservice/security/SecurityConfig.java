package ar.edu.unrn.userservice.security;

import ar.edu.unrn.userservice.security.service.UserDetailsServiceImpl;
import ar.edu.unrn.userservice.security.utils.JwtAuthenticationFilter;
import ar.edu.unrn.userservice.security.utils.JwtConfig;
import ar.edu.unrn.userservice.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private JwtConfig jwtConfig;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public SecurityConfig() {
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                      .antMatchers("/clients/**").hasRole("CLIENTE")
                      .antMatchers("/swagger-ui/**").permitAll()
                      .antMatchers("/v3/**").permitAll()
                      .antMatchers("/users/login").permitAll()
                      .antMatchers("/users/logout").permitAll()
                      .anyRequest().authenticated()
                ).httpBasic().and()
                .csrf().disable()
                .logout()
                .logoutUrl("/logout") // La URL a la que se enviar� la solicitud de logout
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // Invalidar la sesi�n HTTP
                .deleteCookies("JSESSIONID") // Eliminar cookies espec�ficas, si es necesario
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),
                        jwtConfig, jwtUtil, userDetailsService));
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}