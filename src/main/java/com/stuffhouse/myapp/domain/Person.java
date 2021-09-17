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

@Document(collection = "Person")
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String code;
    private double credit;


}
