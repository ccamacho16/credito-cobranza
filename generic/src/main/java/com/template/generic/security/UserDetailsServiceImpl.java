package com.template.generic.security;

import com.template.generic.model.MuUser;
import com.template.generic.repository.MuUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

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
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MuUserRepository muUserRepository;

    //TODO: --> Consultamos los datos del usuario en la Base de Datos.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MuUser muUser = muUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+username+" no existe."));


        log.info("User role: {}", muUser.getRole().getName());
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + muUser.getRole().getName());
        /*Collection<? extends GrantedAuthority> authorities = muUser.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());*/

        return new User(muUser.getUsername(),
                muUser.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singleton(authority));
    }
}
