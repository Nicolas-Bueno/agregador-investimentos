package tech.nb.agregadorinvestimentos.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_billingaddress")
public class BillingAddress {

    @Id
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;
}
