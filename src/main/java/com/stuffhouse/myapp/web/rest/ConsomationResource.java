package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Consomation;
import com.stuffhouse.myapp.service.ConsomationService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/consomation")
public class ConsomationResource {

    private final ConsomationService consomationService;

    public ConsomationResource(ConsomationService consomationService) {
        this.consomationService = consomationService;
    }


    @PostMapping
    public Consomation create(@RequestBody Consomation consomation) {
        return consomationService.insertConsomationData(consomation);
    }

    @GetMapping
    public Collection<Consomation> read() {
        return consomationService.getAllConsomationInformation();
    }

    @GetMapping(path = "{id}")
    public Optional<Consomation> readQueryUsingId(@PathVariable("id") String id) {
        return consomationService.getConsomationInformationById(id);
    }


    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        consomationService.deleteConsomationUsingId(id);
    }
}
