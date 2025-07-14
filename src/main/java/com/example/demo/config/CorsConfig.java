package com.example.demo.config; // ✅ change this to your real base package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
   WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // allow all endpoints
                .allowedOrigins("http://localhost:4200")  // allow frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // allow these HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true);
      }
    };
  }
}
