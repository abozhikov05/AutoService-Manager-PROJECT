package com.abozhikov.AutoServiceManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Деактивиране на CSRF за development (не за продукция)
                .csrf(AbstractHttpConfigurer::disable)

                // Настройка на достъп до страници
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/login", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Формуляр за логин
                .formLogin(form -> form
                        .loginPage("/login") // твоята login страница
                        .defaultSuccessUrl("/home", true) // къде да отиде след успешен вход
                        .permitAll()
                )

                // Логаут
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
