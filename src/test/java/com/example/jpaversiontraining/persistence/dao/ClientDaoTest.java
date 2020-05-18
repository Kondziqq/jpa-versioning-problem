package com.example.jpaversiontraining.persistence.dao;

import com.example.jpaversiontraining.domain.Client;
import com.example.jpaversiontraining.domain.Debt;
import com.example.jpaversiontraining.domain.Payment;
import com.example.jpaversiontraining.persistence.entity.ClientEntity;
import com.example.jpaversiontraining.persistence.entity.DebtEntity;
import com.example.jpaversiontraining.persistence.entity.PaymentEntity;
import com.example.jpaversiontraining.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldNotIncrementChildVersionAfterParentDtoSaved() {

        // given
        var client = Client.builder()
                .firstName("John")
                .lastName("Smith")
                .language("pl")
                .debts(Set.of(
                        Debt.builder()
                                .amount(BigDecimal.valueOf(25000))
                                .payments(Set.of(Payment.builder().amount(BigDecimal.valueOf(2500)).date(Date.from(Instant.now())).build()))
                                .build()))
                .build();

        // when
        long id = clientDao.save(client);
        var clientCopy1 = clientDao.getClientById(id);

        clientCopy1.setLanguage("en");
        id = clientDao.save(clientCopy1);
        var clientCopy2 = clientDao.getClientById(id);

        clientCopy2.setLanguage("en");
        id = clientDao.save(clientCopy2);
        var clientCopy3 = clientDao.getClientById(id);

        // then
        assertEquals(2, clientCopy3.getVersion());
        assertEquals(0, clientCopy3.getDebts().iterator().next().getVersion());
    }

    @Test
    public void shouldNotIncrementChildVersionAfterParentEntitySaved() {

        // given
        var clientEntity = ClientEntity.builder()
                .firstName("John")
                .lastName("Smith")
                .language("pl")
                .debts(Set.of(
                        DebtEntity.builder()
                                .amount(BigDecimal.valueOf(25000))
                                .payments(Set.of(PaymentEntity.builder().amount(BigDecimal.valueOf(2500)).date(Date.from(Instant.now())).build()))
                                .build()))
                .build();

        // when
        var clientEntityCopy = clientRepository.save(clientEntity);

        clientEntityCopy.setLanguage("en");
        clientEntityCopy = clientRepository.save(clientEntityCopy);

        clientEntityCopy.setLanguage("ru");
        clientEntityCopy = clientRepository.save(clientEntityCopy);

        // then
        assertEquals(2, clientEntityCopy.getVersion());
        assertEquals(0, clientEntityCopy.getDebts().iterator().next().getVersion());
    }
}