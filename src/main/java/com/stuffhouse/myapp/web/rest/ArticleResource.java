package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.service.ArticleService;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleResource {

    private final ArticleService articleService;

    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @CrossOrigin("https://stuffhouse.web.app/article")
    @PostMapping
    public Article create(@RequestBody Article Article) {
        return articleService.insertArticleData(Article);
    }

  /**  @GetMapping
    public Collection<Article> read() {
        return articleService.getAllArticleInformation();
    }
*/  @CrossOrigin("https://stuffhouse.web.app/article")
    @GetMapping(path = "{id}")
    public Optional<Article> readQueryUsingId(@PathVariable("id") String id) {
        return articleService.getArticleInformationById(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/article")
    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        articleService.deleteArticleUsingId(id);
    }

    @CrossOrigin("https://stuffhouse.web.app/article")
    @GetMapping("")
    public Page<Article> getArticlesPage(@Filter(entityClass = Article.class) Document document, Pageable pageable) {
        return articleService.getArticlesPage(document, pageable);

    }
}
