package kz.galymbay.diploma.service;

import kz.galymbay.diploma.exception.ServiceFaultException;
import kz.galymbay.diploma.model.entity.Basket;
import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.model.entity.Role;
import kz.galymbay.diploma.repository.BasketRepository;
import kz.galymbay.diploma.repository.ClientRepository;
import kz.galymbay.diploma.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final BasketRepository basketRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Client saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Role role = roleRepository.findByRoleName("ROLE_CLIENT");
        if (Objects.isNull(client.getBasket())) {
            Basket basket = new Basket();
            basket.setClient(client);
            basketRepository.save(basket);
            client.setBasket(basket);
        }

        Set<Role> roles = new HashSet<Role>() {{
            add(role);
        }};
        client.setRoles(roles);
        client.setActivationCode(UUID.randomUUID().toString());

        log.info("Save client {} to DB", client);
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        Client client = clientRepository.findClientById(id);
        log.info("Get client with id: {}, client: {}", id, client);
        return client;
    }

    public Client updateClient(Long id, Client client) {
        Client updatedUser = clientRepository.findClientById(id);

        updatedUser.setFirstName(client.getFirstName());
        updatedUser.setLastName(client.getLastName());
        updatedUser.setEmail(client.getEmail());
        updatedUser.setPhoneNumber(client.getPhoneNumber());
        if (Objects.nonNull(client.getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(client.getPassword()));
        }

        clientRepository.save(updatedUser);

        return updatedUser;
    }

    public String deleteById(Long id) {
        Client client = clientRepository.findClientById(id);
        if (client != null) {
            clientRepository.deleteById(id);
            return "SUCCESS";
        }
        return "UserNotFound";
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public void deleteClothes(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> search(String search) {
        List<Client> result = new ArrayList<>();
        List<Client> clients = getClients();
        for (Client client : clients) {
            if (client.getFirstName().contains(search) || client.getLastName().contains(search) || client.getEmail().contains(search))
                result.add(client);
        }

        return clients;
    }

    public Client registration(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        String uuid = UUID.randomUUID().toString();
        Role role_client = roleRepository.findByRoleName("ROLE_CLIENT");
        if (role_client == null)
            throw new ServiceFaultException("Client role not found", HttpStatus.BAD_REQUEST);
        Basket basket = new Basket();
        basket.setClient(client);
        client.setBasket(basket);
        client.setBlock(true);
        client.setActivationCode(uuid);
        client.getRoles().add(role_client);
        clientRepository.save(client);
        basketRepository.save(basket);

        emailService.sendEmail(
                client.getEmail(),
                "Activation Mail",
                "Hello, to activate your account, follow this link localhost:8084/activate/" + uuid
        );

        return client;
    }

    public boolean login(Client client) {
        Client currentClient = clientRepository.findByEmail(client.getEmail());
        if (Objects.nonNull(currentClient)) {
            return passwordEncoder.encode(client.getPassword()).equals(currentClient.getPassword());
        }

        return false;
    }

    public void activate(String uuid) {
        Client client = clientRepository.findByActivationCode(uuid);
        client.setActivationCode(null);
        client.setBlock(false);

        clientRepository.save(client);

    }
}
