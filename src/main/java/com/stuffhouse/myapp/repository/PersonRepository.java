package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {


    Optional<Person> getPersonByCode(String code);

}
