package ro.esolutions.demotests.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.demotests.entities.Client;
import ro.esolutions.demotests.mappers.ClientMapper;
import ro.esolutions.demotests.models.ClientModel;
import ro.esolutions.demotests.repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository, "clientRepository must not be null");
    }

    public ClientModel save(final ClientModel clientModel) {
        final Client client = clientRepository.save(ClientMapper.toEntity(clientModel));
        return ClientMapper.toModel(client);
    }

    public ClientModel getClientModel(final Long id) {
        return clientRepository.findById(id)
                .map(ClientMapper::toModel)
                .orElseGet(() -> new ClientModel());
    }

    public List<ClientModel> getAllClients() {
            return clientRepository.findAll().stream()
                    .map(ClientMapper::toModel)
                    .collect(Collectors.toList());
    }

    public List<ClientModel> getAllActiveClients() {
        return clientRepository.findByActive(Boolean.TRUE).stream()
                .map(ClientMapper::toModel)
                .collect(Collectors.toList());
    }

    public void deleteClient(final Long id) {
        final Client client = clientRepository.findOne(id);
        client.setActive(false);
        clientRepository.save(client);
    }
}
