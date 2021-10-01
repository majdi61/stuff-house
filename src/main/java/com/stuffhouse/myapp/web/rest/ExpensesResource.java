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
@CrossOrigin(origins = {"https://stuffhouse.web.app"},methods = {RequestMethod.GET ,RequestMethod.POST})
public class ExpensesResource {

    private final ExpensesService expensesService;

    public ExpensesResource(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }


    @PostMapping
    public Expenses create(@RequestBody Expenses expenses) {
        return expensesService.insertExpensesData(expenses);
    }

    /**
     * @GetMapping public Collection<Expenses> read() {
     * return expensesService.getAllExpensesInformation();
     * }
     */
    @GetMapping(path = "{id}")
    public Optional<Expenses> readQueryUsingId(@PathVariable("id") String id) {
        return expensesService.getExpensesInformationById(id);
    }

    @GetMapping("/allexpenses")
    public double getStockCountByType() {
        return expensesService.getExpensesCount();
    }

    @GetMapping("/profits")
    public double getProfits() {
        return expensesService.getProfits();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        expensesService.deleteExpensesUsingId(id);
    }

    @GetMapping("")
    public Page<Expenses> getExpensesPage(@Filter(entityClass = Expenses.class) Document document, Pageable pageable) {
        return expensesService.getExpensesPage(document, pageable);

    }
}
