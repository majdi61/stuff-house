package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Article;
import com.stuffhouse.myapp.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ExpensesService {

    private final ArticleRepository articleRepository;

    public ExpensesService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article insertArticleData(Article article) {
        return articleRepository.save(article);
    }

    public Collection<Article> getAllArticleInformation() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleInformationById(String id) {
        return articleRepository.findById(id);
    }

    public void deleteArticleUsingId(String id) {
        try {
            articleRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public Page<Article> getArticlesPage(Document document, Pageable pageable) {
        return articleRepository.filter(Optional.ofNullable(document).orElse(new Document()), pageable);
    }



}
