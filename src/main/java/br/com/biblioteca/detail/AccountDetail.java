package br.com.biblioteca.detail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import br.com.biblioteca.enums.TypeAccount;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.biblioteca.model.Account;

@Setter
@Getter
public class AccountDetail implements  UserDetails {
	
	private Optional<Account> account = Optional.empty();

	public AccountDetail(){
	}

	public AccountDetail(Optional<Account> account) {
		this.setAccount(account);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

		if(account.isPresent()){
			Account account_ = account.get();

			if(account_.getTypeAccount() == TypeAccount.ADMIN){
				authorities.add(new SimpleGrantedAuthority("ADMIN"));
			}
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return account.orElse(new Account()).getPassword();
	}

	@Override
	public String getUsername() {
		return account.orElse(new Account()).getUser();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
