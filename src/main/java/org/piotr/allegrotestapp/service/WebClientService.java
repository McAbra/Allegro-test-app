package org.piotr.allegrotestapp.service;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientService {

    public WebClient plainGetRequest();

    WebClient httpGetRequestWitToken();
}
