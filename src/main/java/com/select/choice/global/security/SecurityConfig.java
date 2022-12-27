package com.select.choice.global.security;

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
                // auth
                .antMatchers(HttpMethod.POST,"/auth/signin").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/signup").permitAll()
                .antMatchers(HttpMethod.PATCH,"/auth").permitAll()
                .antMatchers(HttpMethod.DELETE,"/auth").authenticated()

                // user
                .antMatchers(HttpMethod.DELETE,"/user").authenticated()
                .antMatchers(HttpMethod.GET,"/user").authenticated()

                // post
                .antMatchers(HttpMethod.GET,"/post/").permitAll()
                .antMatchers(HttpMethod.GET,"/post/list").permitAll()
                .antMatchers(HttpMethod.POST,"/post").permitAll()

                // comment
                .antMatchers(HttpMethod.DELETE,"/comment/**").authenticated()
                .antMatchers(HttpMethod.POST,"/comment/**").authenticated()
                .antMatchers(HttpMethod.PATCH,"/comment/**").authenticated()

                .anyRequest().authenticated();
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
