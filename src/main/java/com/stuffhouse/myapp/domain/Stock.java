package com.stuffhouse.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Document(collection = "Stock")
public class Stock {
    @Id
    private String id;

    @DBRef
    private Article article;
    private long quantity;

    private long cost;

    private String type;

    @JsonIgnore
    @Transient
    @Builder.Default()
    private long quantityPerType = 0;

}
