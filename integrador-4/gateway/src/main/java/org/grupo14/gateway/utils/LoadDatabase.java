package org.grupo14.gateway.utils;

import org.grupo14.gateway.entity.Authority;
import org.grupo14.gateway.repository.AuthorityRepository;
import org.grupo14.gateway.service.UserService;
import org.grupo14.gateway.service.dto.user.UserDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadDatabase {
    private final AuthorityRepository authorityRepository;
    private final UserService userService;

    public  LoadDatabase(AuthorityRepository authorityRepository,  UserService userService) {
        this.authorityRepository = authorityRepository;
        this.userService = userService;
    }

    public void cargarDatos() throws IOException {
        Authority admin = new Authority("ADMIN");
        Authority user = new Authority("USER");
        authorityRepository.save(admin);
        authorityRepository.save(user);
        UserDTO adminDTO = new UserDTO();
        adminDTO.setUsername("admin");
        adminDTO.setPassword("admin");
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        adminRoles.add("USER");
        adminDTO.setAuthorities(adminRoles);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("user");
        userDTO.setPassword("user");
        Set<String> userRoles = new HashSet<>();
        userRoles.add("USER");
        userDTO.setAuthorities(userRoles);
        userService.saveUser(adminDTO);
        userService.saveUser(userDTO);
    }
}
