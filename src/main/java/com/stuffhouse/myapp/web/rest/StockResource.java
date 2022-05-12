package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.service.StockService;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockResource {

    private final StockService stockService;

    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }

    @CrossOrigin("https://stuffhouse.web.app/stock")
    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockService.insertStockData(stock);
    }
    @CrossOrigin("https://stuffhouse.web.app/stock/addstock")
    @PostMapping("/addstock")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }
    @CrossOrigin("https://stuffhouse.web.app/stock")
    @GetMapping("/type/{type}")
    public long getStockCountByType(@PathVariable("type") String type,Pageable pageable) {
        return stockService.getStocksByType(type,pageable);
    }
    @CrossOrigin("https://stuffhouse.web.app/stock")
    @GetMapping("")
    public Page<Stock> getVisitsPage(@Filter(entityClass = Stock.class) Document document, Pageable pageable) {
        return stockService.getStocksPage(document, pageable);
    }

    @CrossOrigin("https://stuffhouse.web.app/stock")
    @GetMapping("/list")
    public List<Stock> getVisitsPage() {
        return stockService.getStocksList();
    }

    @CrossOrigin("https://stuffhouse.web.app/stock")
    @GetMapping(path = "{id}")
    public Optional<Stock> readQueryUsingId(@PathVariable("id") String id) {
        return stockService.getStockInformationById(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/stock")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        stockService.deleteStockUsingId(id);
    }
}
