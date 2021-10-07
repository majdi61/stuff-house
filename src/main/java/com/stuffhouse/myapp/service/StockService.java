package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.domain.Compteur;
import com.stuffhouse.myapp.domain.Expenses;
import com.stuffhouse.myapp.domain.Stock;
import com.stuffhouse.myapp.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
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

    public Stock updateStock(Article article, long quantity, String op) {
        Stock oldStock = stockRepository.findStockByArticle(article);

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

    public List<Stock> getStocksList() {
        return stockRepository.findAll();
    }

    public long getStocksByType(String type, Pageable pageable) {
        Compteur c= new Compteur();
            stockRepository.findStockByType(type,pageable).forEach(a->{
              c.setX(c.getX()+a.getQuantity());



            });
        return  c.getX();
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

    public Stock addStock(Stock stock) {

        Stock oldStock = stockRepository.findStockById(stock.getId());

        oldStock.setQuantity(oldStock.getQuantity() + stock.getQuantity());

       Expenses expenses = Expenses.builder()
            .name(oldStock.getArticle().getName())
            .type(oldStock.getType())
            .quantity(stock.getQuantity())
            .description("alimentation de stock de "+ oldStock.getArticle().getName())
            .cost(stock.getCost())
            .build();


        expensesService.insertExpensesData(expenses);


        return stockRepository.save(oldStock);


    }
}
