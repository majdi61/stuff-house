package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.domain.Expenses;
import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.repository.StockRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    private final ExpensesService expensesService;

    public StockService(StockRepository stockRepository, ExpensesService expensesService) {
        this.stockRepository = stockRepository;
        this.expensesService = expensesService;
    }


    public Stock insertStockData(Stock Stock) {
        return stockRepository.save(Stock);
    }

    public Stock addStockData(Stock Stock) {
        return stockRepository.save(Stock);
    }

    public Stock updateStock(Article aricle, long quantity, String op) {
        Stock oldStock = stockRepository.findStockByArticle(aricle);

        switch (op) {
            case "+":
                oldStock.setQuantity(oldStock.getQuantity() + quantity);
                break;
            case "-":
                oldStock.setQuantity(oldStock.getQuantity() - quantity);
                break;


        }

        return stockRepository.save(oldStock);
    }


    public Page<Stock> getStocksPage(Document document, Pageable pageable) {
        return stockRepository.filter(Optional.ofNullable(document).orElse(new Document()), pageable);
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

    public Stock addStock(Stock stock,double cost) {



        Stock oldStock = stockRepository.findStockById(stock.getId());

        oldStock.setQuantity(oldStock.getQuantity() + stock.getQuantity());

        Expenses expenses = Expenses.builder()
            .name(stock.getArticle().getName())
            .type(stock.getType())
            .quantity(stock.getQuantity())
            .description("alimentation de stock de "+ stock.getArticle().getName())
            .cost(cost)
            .build();


        expensesService.insertExpensesData(expenses);


        return stockRepository.save(oldStock);


    }
}
