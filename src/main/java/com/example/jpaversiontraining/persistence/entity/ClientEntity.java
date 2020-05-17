package com.example.jpaversiontraining.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ClientEntity extends BaseEntity {

    @Id
    @SequenceGenerator(sequenceName = "CLIENT_SEQUENCE", name = "CLIENT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQUENCE")
    private Long id;

    private String firstName;

    private String lastName;

    private String language;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Set<DebtEntity> debts;
}
