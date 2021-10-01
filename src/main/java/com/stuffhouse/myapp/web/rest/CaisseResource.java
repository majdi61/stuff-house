package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Caisse;
import com.stuffhouse.myapp.service.CaisseService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/Caisse")
@CrossOrigin(origins = {"https://stuffhouse.web.app"},methods = {RequestMethod.GET ,RequestMethod.POST})
public class CaisseResource {

    private final CaisseService caisseService;

    public CaisseResource(CaisseService caisseService) {
        this.caisseService = caisseService;
    }


    @PostMapping
    public Caisse create(@RequestBody Caisse caisse) {
        return caisseService.insertCaisseData(caisse);
    }

    @GetMapping
    public Collection<Caisse> read() {
        return caisseService.getAllCaisseInformation();
    }

    @GetMapping(path = "{id}")
    public Optional<Caisse> readQueryUsingId(@PathVariable("id") String id) {
        return caisseService.getCaisseInformationById(id);
    }


    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        caisseService.deleteCaisseUsingId(id);
    }
}
