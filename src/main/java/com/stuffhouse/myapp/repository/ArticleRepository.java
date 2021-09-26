package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.Article;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    @Query("?0")
    Page<Article> filter(Document document, Pageable pageable);


}
