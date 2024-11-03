package tech.nb.agregadorinvestimentos.entity;


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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb-stocks")
public class Stock {

    @Id
    @Column(name="stock_id")
    private String stockId;

    @Column(name = "description")
    private String description;

    @Column(name = "ticker")
    private String ticker;
    
}
