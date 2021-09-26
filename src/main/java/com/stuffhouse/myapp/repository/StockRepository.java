package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.domain.Stock;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    Stock findStockByArticle(Article article);
    Stock findStockById(String id);
    @Query("?0")
    Page<Stock> filter(Document document, Pageable pageable);

}
