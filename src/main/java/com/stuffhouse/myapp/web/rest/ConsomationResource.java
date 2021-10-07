package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.service.ConsomationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public String create(@RequestBody Consomation consomation) {
     return consomationService.insertConsomationData(consomation);
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @PostMapping("/paycredit/{code}")
    public String payCredit(@PathVariable("code") String code) {
        return consomationService.updatePersonCreditIfPayCredit(code);
    }

    @CrossOrigin("https://stuffhouse.web.app/consomation")
    @GetMapping
    public Page<Consomation> read(Pageable pageable) {
        return consomationService.findConsomationsPage(pageable);
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
