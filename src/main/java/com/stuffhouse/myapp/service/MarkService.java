package com.stuffhouse.myapp.service;


import com.stuffhouse.myapp.client.RemoteClient;
import com.stuffhouse.myapp.domain.Mark;
import com.stuffhouse.myapp.repository.MarkRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Service
public class MarkService {

    private final MarkRepository markRepository;

    private final RemoteClient remoteClient;


    public MarkService(MarkRepository markRepository, RemoteClient remoteClient) {
        this.markRepository = markRepository;

        this.remoteClient = remoteClient;
    }

    public Mark saveMark(Mark mark) {
        return markRepository.save(mark);
    }

    public Page<Mark> getMarksPage(Document document, Pageable pageable) {
        document = Optional.ofNullable(document).orElse(new Document());

        return markRepository.filter(document, pageable);
    }

    public Optional<Mark> getMarkById(String id) {
        return markRepository.findById(id);
    }


    public void deleteMark(String id) {
        markRepository.findById(id).ifPresent(markRepository::delete);
    }


    public Object getRoutingPath(String id1, String id2) {

        Mark startMark = markRepository.findById(id1).get();
        Mark endMark = markRepository.findById(id2).get();
        return remoteClient.callRemoteAddressObject(URI.create("https://api.mapbox.com/directions/v5/mapbox/walking/" + startMark.getLongitude() + "," + startMark.getLatitude() + ";" + endMark.getLongitude() + "," + endMark.getLatitude() + "?geometries=geojson&access_token=pk.eyJ1IjoibWFhem91biIsImEiOiJja3lvYzhzZm4ycG4xMnBwMGhhdTd0dzJ1In0.SErz9UjpAZE6k_6tnYYmfA"));
    }
}
