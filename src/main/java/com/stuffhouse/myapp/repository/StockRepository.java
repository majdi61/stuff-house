package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.domain.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

}
