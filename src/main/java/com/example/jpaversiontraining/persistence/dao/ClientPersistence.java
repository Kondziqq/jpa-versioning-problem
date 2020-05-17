package com.example.jpaversiontraining.persistence.dao;

import com.example.jpaversiontraining.domain.Client;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClientPersistence {

    long save(Client client);

    Client getClientById(long id);
}
