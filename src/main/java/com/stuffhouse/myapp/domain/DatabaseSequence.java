package com.stuffhouse.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "database_sequences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

}
