package com.stuffhouse.myapp.service;

import com.stuffhouse.myapp.domain.Article;

import com.stuffhouse.myapp.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article insertArticleData(Article article) {
        return articleRepository.insert(article);
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

}
