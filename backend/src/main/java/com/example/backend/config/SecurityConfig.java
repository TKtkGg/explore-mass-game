package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                // ゲストでも遊べる（ゲーム進行・閲覧）
                .requestMatchers(
                    "/auth/**",
                    "/start",
                    "/move", "/move/**",
                    "/battle", "/battle/**",
                    "/treasure", "/treasure/**",
                    "/shop", "/shop/**",
                    "/status",
                    "/equipment", "/equipment/**",
                    "/card", "/card/**", "/cards",
                    "/items",
                    "/gameover",
                    "/ranking"
                ).permitAll()
                // ログイン必須（セーブ・ランキング登録）
                .requestMatchers("/save/**", "/score/register").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll()
            );
        return http.build();
    }
}
