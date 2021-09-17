package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.repository.ConsomationRepository;
import com.stuffhouse.myapp.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConsomationService {

    private final ConsomationRepository consomationRepository;
    private final StockRepository stockRepository;

    public ConsomationService(ConsomationRepository consomationRepository, StockRepository stockRepository) {
        this.consomationRepository = consomationRepository;
        this.stockRepository = stockRepository;
    }


    public Consomation insertConsomationData(Consomation consomation) {



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
