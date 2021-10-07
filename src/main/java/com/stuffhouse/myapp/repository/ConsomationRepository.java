package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Consomation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsomationRepository extends MongoRepository<Consomation, String> {

    List<Consomation> findConsomationsByCode(String code);

}
