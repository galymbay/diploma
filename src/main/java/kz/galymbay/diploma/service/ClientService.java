package kz.galymbay.diploma.service;

import kz.galymbay.diploma.model.entity.Client;
import kz.galymbay.diploma.model.entity.Role;
import kz.galymbay.diploma.repository.ClientRepository;
import kz.galymbay.diploma.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Client saveClient(Client client) {
//        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Role role = roleRepository.findByName("ROLE_CLIENT");

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

    public Client updateClient(Client client, Long id) {
        Client updatedUser = clientRepository.findClientById(id);

        updatedUser.setFirstName(client.getFirstName());
        updatedUser.setLastName(client.getLastName());
        updatedUser.setEmail(client.getEmail());
        updatedUser.setPhoneNumber(client.getPhoneNumber());
//        updatedUser.setPassword(passwordEncoder.encode(client.getPassword()));
        updatedUser.setPassword(client.getPassword());
        updatedUser.setBlock(client.isBlock());

        clientRepository.save(client);

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
            if (client.getFirstName().contains(search) || client.getLastName().contains(search) || client.getEmail().contains(search) || String.valueOf(client.getId()).contains(search))
                result.add(client);
        }

        return clients;
    }
}
