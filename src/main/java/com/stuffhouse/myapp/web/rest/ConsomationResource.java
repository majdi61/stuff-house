package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.domain.Person;
import com.stuffhouse.myapp.service.ConsomationService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/consomation")
public class ConsomationResource {

    private final ConsomationService consomationService;

    public ConsomationResource(ConsomationService consomationService) {
        this.consomationService = consomationService;
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @PostMapping
    public void create(@RequestBody Consomation consomation) {
     consomationService.insertConsomationData(consomation);
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @PostMapping("/paycredit")
    public Person payCredit(@RequestParam String code) {
        return consomationService.updatePersonCreditIfPayCredit(code);
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @GetMapping
    public Collection<Consomation> read() {
        return consomationService.getAllConsomationInformation();
    }
    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @GetMapping(path = "{id}")
    public Optional<Consomation> readQueryUsingId(@PathVariable("id") String id) {
        return consomationService.getConsomationInformationById(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @GetMapping("/unpaid")
    public double getProfits() {
        return consomationService.getCredits();
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        consomationService.deleteConsomationUsingId(id);
    }
}
