package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsomationRepository extends MongoRepository<Consomation, String> {


    List<Consomation> findConsomationsByCode(String code);

    List<Consomation> findConsomationsByPaid(String paid);


    Page<Consomation> findAll (Pageable pageable);

}
