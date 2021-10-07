package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;



    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person insertPersonData(Person person) {
        return personRepository.save(person);
    }

    public Collection<Person> getAllPersonInformation() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonInformationById(String id) {
        return personRepository.findById(id);
    }

    public Person getPersonByCode(String code) {
        return personRepository.getPersonByCode(code);
    }

    public Person updatePersonUsingId(String id, Person person) {
        Optional<Person> findPersonQuery = personRepository.findById(id);
        Person personValues = findPersonQuery.get();
        personValues.setId(person.getId());
        personValues.setLastName(person.getLastName());
        personValues.setCode(person.getCode());
        personValues.setFirstName(person.getFirstName());
        return personRepository.save(personValues);
    }

    public Person updatePersonCreditUsingId(String id, double credit) {
        Optional<Person> findPersonQuery = personRepository.findById(id);
        Person personValues = findPersonQuery.get();
        personValues.setCredit(personValues.getCredit()+credit);
        return personRepository.save(personValues);
    }

    public void deletePersonUsingId(String id) {
        try {
            personRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
