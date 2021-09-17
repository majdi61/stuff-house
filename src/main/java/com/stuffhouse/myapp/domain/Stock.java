package com.stuffhouse.myapp.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Document(collection = "Stock")
public class Stock {
    @Id
    private String id;
    @DBRef
    @Builder.Default
    private List<Article> Article = Lists.newArrayList();



}
