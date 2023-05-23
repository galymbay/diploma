package kz.galymbay.diploma.repository;

import kz.galymbay.diploma.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByFirstName(String firstName);

    Client findClientById(Long id);

    Client findByPhoneNumber(String phoneNumber);

    Client findByEmail(String email);
}
