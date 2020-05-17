package com.example.jpaversiontraining.persistence.dao;

import com.example.jpaversiontraining.domain.Client;
import com.example.jpaversiontraining.persistence.entity.ClientEntity;
import com.example.jpaversiontraining.persistence.repository.ClientRepository;
import com.example.jpaversiontraining.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientDao implements ClientPersistence {

    private final ClientRepository clientRepository;

    private final Converter converter;

    @Override
    public long save(Client client) {

        var clientEntity = converter.getMapper().map(client, ClientEntity.class);
        return clientRepository.save(clientEntity).getId();
    }

    @Override
    public Client getClientById(long id) {

        var client = clientRepository.getOne(id);
        return converter.getMapper().map(client, Client.class);
    }
}
