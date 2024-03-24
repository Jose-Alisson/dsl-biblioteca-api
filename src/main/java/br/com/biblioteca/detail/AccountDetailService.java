package br.com.biblioteca.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.biblioteca.repository.AccountRepository;

@Service
public class AccountDetailService implements UserDetailsService {
	
	@Autowired
	private AccountRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new AccountDetail(repository.findByUser(username));
	}
	
}
