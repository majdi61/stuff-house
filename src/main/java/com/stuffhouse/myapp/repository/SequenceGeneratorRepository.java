package com.stuffhouse.myapp.repository;

import com.stuffhouse.myapp.domain.DatabaseSequence;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class SequenceGeneratorRepository {

    private final MongoTemplate mongoTemplate;

    public SequenceGeneratorRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long generateSequence(String seqName) {

        DatabaseSequence counter = mongoTemplate.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq", 1), options().returnNew(true).upsert(true),
            DatabaseSequence.class
        );
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
