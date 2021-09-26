package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.repository.ConsomationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConsomationService {

    private final ConsomationRepository consomationRepository;
    private final StockService stockService;
    private final CaisseService caisseService;
    private final PersonService personService;


    public ConsomationService(ConsomationRepository consomationRepository, StockService stockService, CaisseService caisseService, PersonService personService) {
        this.consomationRepository = consomationRepository;
        this.stockService = stockService;

        this.caisseService = caisseService;
        this.personService = personService;
    }


    public Consomation insertConsomationData(Consomation consomation) {

        stockService.updateStock(consomation.getArticle(), consomation.getQuantity(), "-");
        if (consomation.getPaid()) {
            caisseService.updateCaisseUsingId("azeaze", consomation.getValueToPay(), "+");


        } else {
            Person person = consomation.getPerson();
            person.setCredit(person.getCredit() + consomation.getValueToPay());
            personService.insertPersonData(person);
        }


        return consomationRepository.insert(consomation);
    }

    public Collection<Consomation> getAllConsomationInformation() {
        return consomationRepository.findAll();
    }

    public Optional<Consomation> getConsomationInformationById(String id) {
        return consomationRepository.findById(id);
    }

    public void deleteConsomationUsingId(String id) {
        try {
            consomationRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}