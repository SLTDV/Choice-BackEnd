package com.select.choice.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.select.choice.global.filter.ExceptionHandlerFilter;
import com.select.choice.global.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .cors();
        http
                .csrf().disable()
                .httpBasic().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.OPTIONS,"/**").permitAll()

                // auth
                .antMatchers(HttpMethod.POST,"/auth/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/duplication").permitAll()
                .antMatchers(HttpMethod.PATCH,"/auth").permitAll()
                .antMatchers(HttpMethod.DELETE,"/auth").authenticated()
                .antMatchers(HttpMethod.POST, "/auth/phone").permitAll()

                // user
                .antMatchers(HttpMethod.DELETE,"/user").authenticated()
                .antMatchers(HttpMethod.GET,"/user").authenticated()
                .antMatchers(HttpMethod.PATCH,"/user").authenticated()
                .antMatchers(HttpMethod.PATCH,"/user/image").authenticated()
                .antMatchers(HttpMethod.GET, "/user/header").authenticated()

                // post
                .antMatchers(HttpMethod.GET,"/post").authenticated()
                .antMatchers(HttpMethod.GET,"/post/web").permitAll()
                .antMatchers(HttpMethod.GET,"/post/list").authenticated()
                .antMatchers(HttpMethod.GET,"/post/list/web").permitAll()
                .antMatchers(HttpMethod.POST,"/post").authenticated()
                .antMatchers(HttpMethod.POST,"/post/vote/**").authenticated()
                .antMatchers(HttpMethod.GET,"/post/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/post/**").authenticated()

                // comment
                .antMatchers(HttpMethod.DELETE,"/comment/**/**").authenticated()
                .antMatchers(HttpMethod.POST,"/comment/**").authenticated()
                .antMatchers(HttpMethod.PATCH,"/comment/**").authenticated()

                // upload
                .antMatchers(HttpMethod.POST,"/image").authenticated()
                .antMatchers(HttpMethod.POST, "/image/profile").permitAll()


                .anyRequest().denyAll();
        http
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper));
        http
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
