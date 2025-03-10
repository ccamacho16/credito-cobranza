package com.template.generic.security;

import com.template.generic.repository.MuUserRepository;
import com.template.generic.security.filters.JwtAutenticationFilter;
import com.template.generic.security.filters.JwtAuthorizationFilter;
import com.template.generic.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * -------------------------------------------------------------------------*
 * Información General
 * -------------------------------------------------------------------------*
 * Código de Aplicación:
 * Código de Objeto:
 * Descripción:
 * Author Prog.: Crisvel Camacho
 * -------------------------------------------------------------------------*
 * Fecha | Author | Comentario
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
    //TODO: --> Configura la seguridad de Spring Security y registra los filtros personalizados
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    MuUserRepository muUserRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        //TODO: -->SecurityFilterChain: Es el componente central que define las reglas de seguridad HTTP para la aplicación.
        JwtAutenticationFilter jwtAutenticationFilter = new JwtAutenticationFilter(jwtUtils, muUserRepository);
        jwtAutenticationFilter.setAuthenticationManager(authenticationManager);

        httpSecurity.csrf(config -> config.disable())  // se deshabilitar por que en API REST no se manejan cookies de session
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login", "/css/**", "/js/**").permitAll(); //Permite acceso sin autenticación a la ruta /api/login y archivos "/css/**", "/js/**"
                    auth.anyRequest().authenticated(); //Todas las demás rutas requieren autenticación para ser accesibles.
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // para que no se almacene informacion del usuario en el server.
                .addFilter(jwtAutenticationFilter)
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtils, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Configura los orígenes permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Configura los métodos permitidos
        configuration.setAllowedHeaders(List.of("*")); // Configura los encabezados permitidos
        configuration.setAllowCredentials(true); // Permitir credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //source.registerCorsConfiguration("/auth/**", configuration);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
