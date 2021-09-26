package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Expenses;
import com.stuffhouse.myapp.repository.ExpensesRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
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


}
