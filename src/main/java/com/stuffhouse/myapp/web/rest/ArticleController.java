package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping
    public Article create(@RequestBody Article Article) {
        return articleService.insertArticleData(Article);
    }

    @GetMapping
    public Collection<Article> read() {
        return articleService.getAllArticleInformation();
    }

    @GetMapping(path = "{id}")
    public Optional<Article> readQueryUsingId(@PathVariable("id") String id) {
        return articleService.getArticleInformationById(id);
    }


    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        articleService.deleteArticleUsingId(id);
    }
}
