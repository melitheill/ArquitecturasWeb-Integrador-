package org.grupo14.gateway.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.grupo14.gateway.entity.User;
import org.grupo14.gateway.repository.AuthorityRepository;
import org.grupo14.gateway.repository.UserRepository;
import org.grupo14.gateway.service.dto.user.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public long saveUser(UserDTO request){
        final var user = new User(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        final var roles = this.authorityRepository.findAllById(request.getAuthorities());
        user.setAuthorities(roles);
        final var result = userRepository.save(user);
        return result.getId();
    }
}
