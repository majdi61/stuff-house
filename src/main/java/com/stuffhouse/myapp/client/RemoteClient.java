package com.stuffhouse.myapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;


@FeignClient(name = "RemoteClient", url = "https://placeholder.com")
public interface RemoteClient {


    @GetMapping(path = "")
    String callRemoteAddress(URI baseUrl);

    @GetMapping(path = "")
    Object callRemoteAddressObject(URI baseUrl);

    @PostMapping(path = "")
    ResponseEntity callRemoteAddressPost(URI baseUrl);


}
