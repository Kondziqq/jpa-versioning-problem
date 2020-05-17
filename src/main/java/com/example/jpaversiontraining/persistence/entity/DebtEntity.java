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
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DebtEntity extends BaseEntity {

    @Id
    @SequenceGenerator(sequenceName = "DEBT_SEQUENCE", name = "DEBT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_SEQUENCE")
    private Long id;

    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "debt_id")
    private Set<PaymentEntity> payments;
}
