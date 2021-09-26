package com.stuffhouse.myapp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String description;
    private double cost;

}
