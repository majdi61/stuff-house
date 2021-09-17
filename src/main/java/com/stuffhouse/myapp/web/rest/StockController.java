package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.insertStockData(stock);
    }

    @GetMapping
    public Collection<Stock> read() {
        return stockService.getAllStockInformation();
    }

    @GetMapping(path = "{id}")
    public Optional<Stock> readQueryUsingId(@PathVariable("id") String id) {
        return stockService.getStockInformationById(id);
    }


    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        stockService.deleteStockUsingId(id);
    }
}
