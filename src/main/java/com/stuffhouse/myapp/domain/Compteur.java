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

public class Compteur {
    @Builder.Default()
    private long x=0;
    @Builder.Default()
    private double d=0;

    @Builder.Default()
    private double s=0;


}
