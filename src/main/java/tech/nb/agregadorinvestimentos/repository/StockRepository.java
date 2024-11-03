package tech.nb.agregadorinvestimentos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.nb.agregadorinvestimentos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String>{

}
