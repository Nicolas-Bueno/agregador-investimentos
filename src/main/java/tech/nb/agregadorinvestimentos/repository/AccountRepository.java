package tech.nb.agregadorinvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.nb.agregadorinvestimentos.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{

}
