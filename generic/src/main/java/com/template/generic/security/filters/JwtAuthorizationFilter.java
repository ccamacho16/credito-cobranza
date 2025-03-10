package com.template.generic.security.filters;

import com.template.generic.security.jwt.JwtUtils;
import com.template.generic.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
public class JwtAuthorizationFilter extends OncePerRequestFilter {  //--OK
    //TODO: --> Es un filtro que valida el token JWT en cada solicitud después del inicio de sesión.

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    /*Este metodo se ejecuta una vez por solicitud entrante.
        Propósito:
        Validar la autenticidad del token JWT incluido en la solicitud.
        Autenticar al usuario asociado con el token para que pueda acceder a recursos protegidos.
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7); // a partir del 7 toma todoo lo que estan delante.

            if (jwtUtils.isTokenValid(token)){
                String username = jwtUtils.getUserNameFromToken(token);

                //TODO -> CONSULTA LAS CREDENCIALES EN LA BASE DE DATOS.
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities());

                System.out.println("--*--- User Authorities: " + userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        //Si no cumple con la condiciona nos va a denegar el acceso.
        filterChain.doFilter(request, response);
    }
}
    /*
    * Resumen del flujo:
        1. La solicitud entrante incluye un encabezado Authorization con un token JWT.
        2. El filtro verifica:
            - Si el encabezado existe y comienza con "Bearer ".
            - Si el token es válido.
        3. Si el token es válido:
            - Extrae el nombre de usuario del token.
            - Carga los detalles del usuario desde la base de datos.
            - Establece la autenticación del usuario en el contexto de seguridad.
        4. Si el token no es válido o el encabezado está ausente, no autentica al usuario.
        5. En cualquier caso, la solicitud sigue su curso a través de la cadena de filtros.

    * */
