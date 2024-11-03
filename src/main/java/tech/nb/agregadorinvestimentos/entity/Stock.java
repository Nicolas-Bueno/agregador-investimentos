package tech.nb.agregadorinvestimentos.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb-stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="stock_id")
    private UUID stock;

    @Column(name = "description")
    private String description;

    @Column(name = "ticker")
    private String ticker;
    
}
