package com.soares.cadastrocliente.api.controller.security.services;

import com.soares.cadastrocliente.api.controller.security.JwtUserFactory;
import com.soares.cadastrocliente.api.entities.Usuario;
import com.soares.cadastrocliente.api.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	public static final Logger log = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioService.buscarPorEmail(username);
		log.info("Usuário {}", usuario.isPresent());
		if (usuario.isPresent()) {
			return JwtUserFactory.create(usuario.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
