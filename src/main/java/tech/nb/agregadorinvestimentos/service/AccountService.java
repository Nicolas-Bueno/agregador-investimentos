package tech.nb.agregadorinvestimentos.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech.nb.agregadorinvestimentos.dto.AssocieteAccountStockDto;
import tech.nb.agregadorinvestimentos.entity.AccountStock;
import tech.nb.agregadorinvestimentos.entity.AccountStockId;
import tech.nb.agregadorinvestimentos.repository.AccountRepository;
import tech.nb.agregadorinvestimentos.repository.AccountStockRepository;
import tech.nb.agregadorinvestimentos.repository.StockRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRepository accountStockRepository;

    public void associateStock(String accountId, AssocieteAccountStockDto dto){

        var account = accountRepository.findById(UUID.fromString(accountId))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());

        var entity = new AccountStock(
            id,
            account,
            stock,
            dto.quantity()
        );

        accountStockRepository.save(entity);
    }

}
