package ro.esolutions.demotests.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.demotests.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);

    List<Client> findByEmailAddress(String emailAddress);

    List<Client> findByActive(Boolean active);
}
