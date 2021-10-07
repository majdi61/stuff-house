package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Caisse;
import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.repository.CaisseRepository;
import com.stuffhouse.myapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CaisseService {

    private final CaisseRepository caisseRepository;

    private final PersonRepository personRepository;

    private final ConsomationService consomationService;

    public CaisseService(CaisseRepository caisseRepository, PersonRepository personRepository, ConsomationService consomationService) {
        this.caisseRepository = caisseRepository;
        this.personRepository = personRepository;
        this.consomationService = consomationService;
    }

    public Caisse insertCaisseData(Caisse caisse) {
        return caisseRepository.save(caisse);
    }

    public Collection<Caisse> getAllCaisseInformation() {
        return caisseRepository.findAll();
    }

    public Optional<Caisse> getCaisseInformationById(String id) {
        return caisseRepository.findById(id);
    }

    public Caisse updateCaisseUsingId(String id,double amount, String op) {
        Optional<Caisse> findCaisseQuery = Optional.ofNullable(caisseRepository.findCaisseById(id));
        Caisse caisseValues = findCaisseQuery.get();

        double caisseOldValue = caisseValues.getValeur();


        switch (op) {
            case "+":
                caisseValues.setValeur(caisseOldValue + amount);
                break;
            case "-":
                caisseValues.setValeur(caisseOldValue - amount);
                break;

        }


        return caisseRepository.save(caisseValues);
    }
    public void updateCaisseIfConsomationPaid(String id,double amount) {



        caisseRepository.findById(id).ifPresent(caisse -> {
            caisse.setValeur(caisse.getValeur()+amount);
            caisseRepository.save(caisse);
        });
    }




    public void deleteCaisseUsingId(String id) {
        try {
            caisseRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    public Person updatePersonCreditIfPayCredit(String code) {
        Optional<Person> findPersonQuery = Optional.ofNullable(personRepository.getPersonByCode(code));
        Person personValues = findPersonQuery.get();


        consomationService.getConsomationsByCode(personValues.getCode()).forEach(consomation -> {
            if(!consomation.getPaid())
                consomation.setPaid(true);
            consomationService.updateConsomationsIfPaid(consomation);


        });
        updateCaisseIfConsomationPaid("615e3266d5f0b54a6ba4a249",personValues.getCredit());
        personValues.setCredit(0);
        return personRepository.save(personValues);
    }

}
