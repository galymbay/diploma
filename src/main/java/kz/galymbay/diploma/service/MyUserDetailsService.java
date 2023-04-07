package kz.galymbay.diploma.service;

import kz.galymbay.diploma.model.dto.MyUserPrincipal;
import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByFirstName(username);
        if (client == null)
            throw new UsernameNotFoundException(username);

        return new MyUserPrincipal(client);
    }
}
