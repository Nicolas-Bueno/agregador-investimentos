package tech.nb.agregadorinvestimentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.nb.agregadorinvestimentos.dto.AccountStockResponseDto;
import tech.nb.agregadorinvestimentos.dto.AssocieteAccountStockDto;
import tech.nb.agregadorinvestimentos.service.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                        @RequestBody AssocieteAccountStockDto dto){

        accountService.associateStock(accountId, dto);                    
        
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> associateStock(@PathVariable("accountId") String accountId){

        var stocks = accountService.listStocks(accountId);                    
        
        return ResponseEntity.ok(stocks);
    }
        


}
