package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.domain.Expenses;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends MongoRepository<Expenses, String> {

    @Query("?0")
    Page<Expenses> filter(Document document, Pageable pageable);


}
