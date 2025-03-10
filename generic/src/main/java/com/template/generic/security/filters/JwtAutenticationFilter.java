package com.template.generic.security.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.generic.exception.OperationException;
import com.template.generic.model.MuUser;
import com.template.generic.repository.MuUserRepository;
import com.template.generic.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
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
 * 15.01.2025 | Crisvel Camacho | Creación Inicial
 * -------------------------------------------------------------------------*
 */
public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {  //--OK
    //TODO: --> Es un filtro que gestiona la autenticación cuando un usuario intenta iniciar sesión.
    private JwtUtils jwtUtils;

    private final MuUserRepository muUserRepository;

    public JwtAutenticationFilter(JwtUtils jwtUtils,
                                  MuUserRepository muUserRepository){
        this.jwtUtils = jwtUtils;
        this.muUserRepository = muUserRepository;
        setFilterProcessesUrl("/auth/login");

    }

    //TODO: --> Intenta de Autenticar al usuario cuando se recibe una solicitud de inicio de session.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        MuUser muUser = null;
        String username="";
        String password="";
        try {
            muUser = new ObjectMapper().readValue(request.getInputStream(), MuUser.class); //Mapea el request a un UserEntity
            username = muUser.getUsername();
            password = muUser.getPassword();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    //TODO: --> Este metodo se ejecuta cuando la autentificacion es exitosa. Se crea un Token.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccesToken(user.getUsername());  // se crea un token. Este token se enviara al cliente como respuesta http.

        response.addHeader("Authorization", token);

        MuUser muUser = this.muUserRepository.findByUsername(user.getUsername())
                .orElseThrow(()->new OperationException("No existe un Usuario con username: "+user.getUsername()));

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticación Correcta");
        httpResponse.put("username", user.getUsername());
        httpResponse.put("role", muUser.getRole().getName());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse)); //convertimos el Map en JSon
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
