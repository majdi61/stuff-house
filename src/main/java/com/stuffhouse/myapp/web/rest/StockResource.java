package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.service.StockService;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockResource {

    private final StockService stockService;

    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }


    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.insertStockData(stock);
    }

    @PostMapping("/addstock")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

    @GetMapping("/type/{type}")
    public long getStockCountByType(@PathVariable("type") String type,Pageable pageable) {
        return stockService.getStocksByType(type,pageable);
    }

    @GetMapping("")
    public Page<Stock> getVisitsPage(@Filter(entityClass = Stock.class) Document document, Pageable pageable) {
        return stockService.getStocksPage(document, pageable);
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
