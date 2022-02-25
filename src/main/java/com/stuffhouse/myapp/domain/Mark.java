package com.stuffhouse.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "mark")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Mark {

    @Id
    private String id;

    @Indexed(unique = true)
    private long uiid;

    private String name;

    private String latitude;

    private String longitude;

    private String tags;

    private String categories;

    private Object data;

    private String Description;

    private String shortDescription;

    private List<String> photos = Lists.newArrayList();

}
