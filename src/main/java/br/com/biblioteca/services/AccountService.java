package br.com.biblioteca.services;

import br.com.biblioteca.detail.AccountDetail;
import br.com.biblioteca.dto.AccountDTO;
import br.com.biblioteca.enums.TypeAccount;
import br.com.biblioteca.exception.causable.ErrDateTransfer;
import br.com.biblioteca.jwt.TokenService;
import br.com.biblioteca.model.Account;
import br.com.biblioteca.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final ModelMapper mapper = new ModelMapper();

    public String login(String user, String password) {
        Optional<Account> opt = repository.findByUser(user);

        if (opt.isPresent()) {
            var authToken = new UsernamePasswordAuthenticationToken(user, password);
            Authentication auth = authenticationManager.authenticate(authToken);

            var response = ((AccountDetail) auth.getPrincipal()).getAccount();
            return tokenService.generateToken(response.get());

        }
        throw new ErrDateTransfer("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

    public AccountDTO create(Account account) {
        account.setTypeAccount(TypeAccount.USER);
        account.setPassword(encoder.encode(account.getPassword()));
        return mapper.map(repository.save(account), AccountDTO.class);
    }

    public void delete(Long id) {

    }

    public AccountDTO getAccount() {
        return null;
    }
}
