package com.example.jpaversiontraining.util;

import com.example.jpaversiontraining.domain.BaseDto;
import com.example.jpaversiontraining.domain.Client;
import com.example.jpaversiontraining.domain.Debt;
import com.example.jpaversiontraining.domain.Payment;
import com.example.jpaversiontraining.persistence.entity.BaseEntity;
import com.example.jpaversiontraining.persistence.entity.ClientEntity;
import com.example.jpaversiontraining.persistence.entity.DebtEntity;
import com.example.jpaversiontraining.persistence.entity.PaymentEntity;
import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class Converter {

    private MapperFacade mapper;

    @PostConstruct
    public void init() {

        var mapperFactory = new DefaultMapperFactory.Builder().build();
        registerMappings(mapperFactory);
        mapper = mapperFactory.getMapperFacade();
    }

    private void registerMappings(DefaultMapperFactory mapperFactory) {

        mapperFactory.classMap(BaseDto.class, BaseEntity.class).byDefault().register();
        mapperFactory.classMap(Client.class, ClientEntity.class).byDefault().register();
        mapperFactory.classMap(Debt.class, DebtEntity.class).byDefault().register();
        mapperFactory.classMap(Payment.class, PaymentEntity.class).byDefault().register();

        mapperFactory.classMap(BaseEntity.class, BaseDto.class).byDefault().register();
        mapperFactory.classMap(ClientEntity.class, Client.class).byDefault().register();
        mapperFactory.classMap(DebtEntity.class, Debt.class).byDefault().register();
        mapperFactory.classMap(PaymentEntity.class, Payment.class).byDefault().register();
    }
}
