package br.com.teste.Aplicacao.service;

import br.com.teste.Aplicacao.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioDetalhesService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;


    private final Map<String, Usuario> users = new HashMap<>();


    public UsuarioDetalhesService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users.put("user", new Usuario("user", passwordEncoder.encode("password"), Arrays.asList("ROLE_USER")));
        users.put("admin", new Usuario("admin", passwordEncoder.encode("adminpass"), Arrays.asList("ROLE_USER", "ROLE_ADMIN")));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(users.get(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}