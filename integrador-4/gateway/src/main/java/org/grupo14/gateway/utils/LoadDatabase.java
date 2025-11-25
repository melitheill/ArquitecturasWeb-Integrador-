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
        Authority premiumUser = new Authority("PREMIUM_USER");
        authorityRepository.save(admin);
        authorityRepository.save(user);
        authorityRepository.save(premiumUser);
        UserDTO adminDTO = new UserDTO();
        adminDTO.setUsername("admin");
        adminDTO.setPassword("admin");
        Set<String> adminRoles = new HashSet<>();
        adminRoles.add("ADMIN");
        adminRoles.add("USER");
        adminRoles.add("PREMIUM_USER");
        adminDTO.setAuthorities(adminRoles);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("user");
        userDTO.setPassword("user");
        Set<String> userRoles = new HashSet<>();
        userRoles.add("USER");
        userDTO.setAuthorities(userRoles);
        UserDTO premiumUserDTO = new UserDTO();
        premiumUserDTO.setUsername("premium_user");
        premiumUserDTO.setPassword("premium_user");
        Set<String> premiumUserRoles = new HashSet<>();
        premiumUserRoles.add("USER");
        premiumUserRoles.add("PREMIUM_USER");
        premiumUserDTO.setAuthorities(premiumUserRoles);
        userService.saveUser(adminDTO);
        userService.saveUser(userDTO);
        userService.saveUser(premiumUserDTO);
    }
}
