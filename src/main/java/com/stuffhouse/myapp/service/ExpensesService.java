package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Compteur;
import com.stuffhouse.myapp.domain.Expenses;
import com.stuffhouse.myapp.repository.ConsomationRepository;
import com.stuffhouse.myapp.repository.ExpensesRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    private final ConsomationRepository consomationRepository;

    public ExpensesService(ExpensesRepository expensesRepository, ConsomationRepository consomationRepository) {
        this.expensesRepository = expensesRepository;
        this.consomationRepository = consomationRepository;
    }

    public Expenses insertExpensesData(Expenses expenses) {
        return expensesRepository.save(expenses);
    }



    public Optional<Expenses> getExpensesInformationById(String id) {
        return expensesRepository.findById(id);
    }

    public void deleteExpensesUsingId(String id) {
        try {
            expensesRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public Page<Expenses> getExpensesPage(Document document, Pageable pageable) {
        return expensesRepository.filter(Optional.ofNullable(document).orElse(new Document()), pageable);
    }


    public double getExpensesCount() {
        Compteur c= new Compteur();
        expensesRepository.findAll().forEach(a->{
            c.setD(c.getD()+a.getCost());



        });
        return  c.getD();

    }

    public double getProfits() {

        Compteur c= new Compteur();
        consomationRepository.findConsomationsByPaid("true").forEach(a->{
            c.setD(c.getD()+a.getValueToPay());



        });

        expensesRepository.findAll().forEach(a->{
            c.setS(c.getS()+a.getCost());



        });



        return c.getD()-c.getS();
    }
}
