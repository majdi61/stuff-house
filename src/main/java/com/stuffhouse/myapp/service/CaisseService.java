package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Caisse;
import com.stuffhouse.myapp.repository.CaisseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CaisseService {

    private final CaisseRepository caisseRepository;

    public CaisseService(CaisseRepository caisseRepository) {
        this.caisseRepository = caisseRepository;
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
        Optional<Caisse> findCaisseQuery = caisseRepository.findById(id);
        Caisse oldCaisse = findCaisseQuery.get();

        double caisseOldValue = oldCaisse.getValeur();


        switch (op) {
            case "+":
                oldCaisse.setValeur(caisseOldValue + amount);
                break;
            case "-":
                oldCaisse.setValeur(caisseOldValue - amount);
                break;

        }


        return caisseRepository.save(oldCaisse);
    }


    public void deleteCaisseUsingId(String id) {
        try {
            caisseRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

}
