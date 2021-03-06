package com.stuffhouse.myapp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "expenses")
public class Expenses {
    @Id
    private String id;
    private String name;
    private String type;
    private long quantity;
    private String description;
    private double cost;

    @Builder.Default
    private Instant time = Instant.now();


}
