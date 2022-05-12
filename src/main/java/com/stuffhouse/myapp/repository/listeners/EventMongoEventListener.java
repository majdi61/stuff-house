package com.stuffhouse.myapp.repository.listeners;

import com.stuffhouse.myapp.domain.Event;
import com.stuffhouse.myapp.repository.SequenceGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class EventMongoEventListener extends AbstractMongoEventListener<Event> {

    private final SequenceGeneratorRepository sequenceGeneratorRepository;

    @Autowired
    public EventMongoEventListener(SequenceGeneratorRepository sequenceGeneratorRepository) {
        this.sequenceGeneratorRepository = sequenceGeneratorRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Event> event) {
        if (event.getSource().getUiid() < 1) {
            event.getSource().setUiid(sequenceGeneratorRepository.generateSequence(Event.SEQUENCE_NAME_UIID));
        }
    }

}
