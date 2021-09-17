package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public Stock insertStockData(Stock Stock) {
        return stockRepository.insert(Stock);
    }

    public Collection<Stock> getAllStockInformation() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockInformationById(String id) {
        return stockRepository.findById(id);
    }

    public void deleteStockUsingId(String id) {
        try {
            stockRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
