package com.template.generic.controller;

import com.template.generic.exception.OperationException;
import com.template.generic.model.MuUser;
import com.template.generic.repository.MuUserRepository;
import com.template.generic.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;
import java.util.HashMap;
import java.util.Map;

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
 * 16.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class MuLoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public MuLoginController(AuthenticationManager authenticationManager,
                             JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /*@GetMapping("/login")
    public String login() {
        return "login"; // Retorna el nombre de la vista para el formulario de inicio de sesión
    }*/
    /*@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));
            String token = jwtUtils.generateAccesToken(credentials.get("username"));


            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", jwtUtils.extractAllClaims(token).getSubject());
            response.put("message", "Autenticación exitosa");
            return ResponseEntity.ok(response);
            return ResponseEntity.ok().build();
        }catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(createErrorResponse("Error de autenticación", "Credenciales incorrectas"));
        } catch (DisabledException e) {
            return ResponseEntity.status(403).body(createErrorResponse("Error de autenticación", "Cuenta deshabilitada"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(createErrorResponse("Error del servidor", "Ocurrió un error inesperado"));
        }
    }*/

    private Map<String, Object> createErrorResponse(String title, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("title", title);
        errorResponse.put("message", message);
        return errorResponse;
    }

}
