package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Expenses;
import com.stuffhouse.myapp.service.ExpensesService;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesResource {

    private final ExpensesService expensesService;

    public ExpensesResource(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @PostMapping
    public Expenses create(@RequestBody Expenses expenses) {
        return expensesService.insertExpensesData(expenses);
    }

    /**
     * @GetMapping public Collection<Expenses> read() {
     * return expensesService.getAllExpensesInformation();
     * }
     */
    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @GetMapping(path = "{id}")
    public Optional<Expenses> readQueryUsingId(@PathVariable("id") String id) {
        return expensesService.getExpensesInformationById(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @GetMapping("/allexpenses")
    public double getStockCountByType() {
        return expensesService.getExpensesCount();
    }

    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @GetMapping("/profits")
    public double getProfits() {
        return expensesService.getProfits();
    }

    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        expensesService.deleteExpensesUsingId(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/expenses")
    @GetMapping("")
    public Page<Expenses> getExpensesPage(@Filter(entityClass = Expenses.class) Document document, Pageable pageable) {
        return expensesService.getExpensesPage(document, pageable);

    }
}
