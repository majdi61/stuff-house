package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }
    @CrossOrigin("https://stuffhouse.web.app/person")
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.insertPersonData(person);
    }

    @CrossOrigin("https://stuffhouse.web.app/person")
    @GetMapping
    public Collection<Person> read() {
        return personService.getAllPersonInformation();
    }
    @CrossOrigin("https://stuffhouse.web.app/person")
    @GetMapping(path = "{id}")
    public Optional<Person> readQueryUsingId(@PathVariable("id") String id) {
        return personService.getPersonInformationById(id);
    }
    @CrossOrigin("https://stuffhouse.web.app/person")
    @PutMapping(path = "/update/{id}")
    public void update(@PathVariable String id, @RequestBody Person person) {
        personService.updatePersonUsingId(id, person);
    }

    @CrossOrigin("https://stuffhouse.web.app/person")
    @PutMapping(path = "/paycredit")
    public void payCredit( @RequestBody String code) {
        personService.updatePersonCreditIfPayCredit(code);
    }

    @CrossOrigin("https://stuffhouse.web.app/person")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        personService.deletePersonUsingId(id);
    }
}
