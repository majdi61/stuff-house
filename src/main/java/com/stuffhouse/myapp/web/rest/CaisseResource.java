package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Caisse;
import com.stuffhouse.myapp.service.CaisseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/caisse")
public class CaisseResource {

    private final CaisseService caisseService;

    public CaisseResource(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @CrossOrigin("https://stuffhouse.web.app/Caisse")
    @PostMapping
    public Caisse create(@RequestBody Caisse caisse) {
        return caisseService.insertCaisseData(caisse);
    }

    @CrossOrigin("https://stuffhouse.web.app/Caisse")
    @GetMapping
    public Collection<Caisse> read() {
        return caisseService.getAllCaisseInformation();
    }

    @CrossOrigin("https://stuffhouse.web.app/Caisse")
    @GetMapping(path = "{id}")
    public Optional<Caisse> readQueryUsingId(@PathVariable("id") String id) {
        return caisseService.getCaisseInformationById(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/Caisse")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        caisseService.deleteCaisseUsingId(id);
    }
}
