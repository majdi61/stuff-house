package com.stuffhouse.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Transient;
import java.util.Date;

@Document(collection = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Event {

    @Transient
    public static final String SEQUENCE_NAME_UIID = "event_sequence_uiid";
    @Id
    private String id;

    @Indexed(unique = true)
    private long uiid;

    private String name;

    private String description;

    @DBRef
    private Mark place;

    private String img;

    private Date date;

    private String price;

}
