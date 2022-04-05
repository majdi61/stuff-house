package com.stuffhouse.myapp.repository;


import com.stuffhouse.myapp.domain.Event;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String> {

    @Query("?0")
    Page<Event> filter(Document document, Pageable pageable);


}
