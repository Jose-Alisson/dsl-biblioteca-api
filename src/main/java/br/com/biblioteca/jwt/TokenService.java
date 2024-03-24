package br.com.biblioteca.jwt;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.biblioteca.model.Account;

@Service
public class TokenService {

	@Value("${chave.secreta.jwt.aplicacao}")
	private String chave;

	public String generateToken(Account account) {
		Date dateExpiration = Date.from(LocalDateTime.now().plusDays(3).toInstant(ZoneOffset.of("-03:00")));

		return JWT.create().withSubject(account.getUser()).withExpiresAt(dateExpiration)
				.sign(Algorithm.HMAC256(chave));
	}
	
	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(chave)).build().verify(token).getSubject();
	}
}
