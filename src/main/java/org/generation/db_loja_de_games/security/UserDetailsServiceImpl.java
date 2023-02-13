package org.generation.db_loja_de_games.security;

import java.util.Optional;

import org.generation.db_loja_de_games.model.Usuario;
import org.generation.db_loja_de_games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;	

@Service
public class UserDetailsServiceImpl {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> Usuario = usuarioRepository.findByUsuario(userName);

		if (Usuario.isPresent())
			return new UserDetailsImpl(Usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
}