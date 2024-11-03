package tech.nb.agregadorinvestimentos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.nb.agregadorinvestimentos.entity.AccountStock;
import tech.nb.agregadorinvestimentos.entity.AccountStockId;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId>{

}
