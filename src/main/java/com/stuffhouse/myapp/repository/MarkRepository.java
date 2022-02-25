package com.stuffhouse.myapp.repository;


import com.stuffhouse.myapp.domain.Mark;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MarkRepository extends MongoRepository<Mark, String> {

    @Query("?0")
    Page<Mark> filter(Document document, Pageable pageable);


}
