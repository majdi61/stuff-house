package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Mark;
import com.stuffhouse.myapp.service.MarkService;
import com.stuffhouse.myapp.service.dto.Review;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/mark")
public class MarkResource {

    private final MarkService markService;


    public MarkResource(MarkService markService) {
        this.markService = markService;
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @PostMapping(path = "")
    public ResponseEntity<Mark> saveMark(@RequestBody Mark mark) {
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(markService.saveMark(mark)));
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @PostMapping(path = "/review/{id}")
    public ResponseEntity<Mark> addReview(@PathVariable String id,@RequestBody Review review) {
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(markService.addReview(id,review)));
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @GetMapping(path = "")
    public Page<Mark> getMarksPage(@Filter(entityClass = Mark.class) Document document, Pageable pageable) {
        return markService.getMarksPage(document, pageable);
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Mark> getMarkById(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(markService.getMarkById(id));
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        markService.deleteMark(id);
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @GetMapping(path = "/rout/{id1}/{id2}")
    public Object getRoutingPath(@PathVariable String id1, @PathVariable String id2) {
        return markService.getRoutingPath(id1, id2);
    }

}
