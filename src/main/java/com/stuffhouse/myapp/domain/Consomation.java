package com.stuffhouse.myapp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "Consomation")
public class Consomation {
    @Id
    private String id;
    @DBRef
    private Person person;
    @DBRef
    private Article article;

    private long quantity;

    private double valueToPay;
    private Boolean paid;


    @Builder.Default
    private Instant time = Instant.now();

    public Consomation(Person person, Article article, Boolean paid) {
    }
}
