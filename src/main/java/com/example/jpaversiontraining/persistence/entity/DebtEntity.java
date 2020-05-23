package com.example.jpaversiontraining.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DebtEntity extends BaseEntity {

    @Id
    @SequenceGenerator(sequenceName = "DEBT_SEQUENCE", name = "DEBT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_SEQUENCE")
    private Long id;

    private BigDecimal amount;

    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL)
    private Set<PaymentEntity> payments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    public void addPayment(PaymentEntity paymentEntity) {

        if (payments == null) {
            payments = new HashSet<>();
        }

        payments.add(paymentEntity);
        paymentEntity.setDebt(this);
    }
}
