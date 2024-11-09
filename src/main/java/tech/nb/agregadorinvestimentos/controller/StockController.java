package tech.nb.agregadorinvestimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.nb.agregadorinvestimentos.dto.CreateStockDto;
import tech.nb.agregadorinvestimentos.service.StockService;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){

        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
