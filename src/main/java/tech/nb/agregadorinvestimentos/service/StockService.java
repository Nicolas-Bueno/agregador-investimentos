package tech.nb.agregadorinvestimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.nb.agregadorinvestimentos.dto.CreateStockDto;
import tech.nb.agregadorinvestimentos.entity.Stock;
import tech.nb.agregadorinvestimentos.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void createStock(CreateStockDto createStockDto){

        var stock = new Stock(
                    createStockDto.stockId(),
                    createStockDto.description(),
                    null
        );

        stockRepository.save(stock);
    }
}
