package it.me.tae.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableOAuth2Sso
@RestController
@SpringBootApplication
public class GoogleOauthSpringBootSecurityApplication extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/index.html", "/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(GoogleOauthSpringBootSecurityApplication.class).run(args);
    }
}
