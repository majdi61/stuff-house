package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Caisse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaisseRepository extends MongoRepository<Caisse, String> {
    Caisse findCaisseById(String id);

}
