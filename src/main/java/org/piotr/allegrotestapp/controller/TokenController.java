package org.piotr.allegrotestapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.piotr.allegrotestapp.model.Token;
import org.piotr.allegrotestapp.service.AllegroJwtToken;
import org.piotr.allegrotestapp.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class TokenController {

    @Autowired
    private AllegroJwtToken allegroJwtToken;
    @Autowired
    private RestTemplate template;
    @Autowired
    WebClientService webClientService;

    @GetMapping("/getallegrotoken")
    public void getAllegroToken() {
        String token = WebClient.builder()
                .build().post().uri(uriBuilder -> uriBuilder.path("https://allegro.pl.allegrosandbox.pl/auth/oauth/token")
                .queryParam("grant_type", "client_credentials")
                .build())
                .header("username", allegroJwtToken.getClientId())
                .header("password", allegroJwtToken.getClientSecret())
                .retrieve().bodyToMono(String.class).block();
        System.out.println(token);
    }


    @RequestMapping("/allegroauthcode")
    public void returnMessage(@RequestParam String code) throws JsonProcessingException {
        allegroJwtToken.setAuthCode(code);
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.readValue(allegroJwtToken.requestJWT(), Token.class);
        allegroJwtToken.setAllegroJwtToken(token);
        System.out.println(allegroJwtToken.getAllegroJwtToken().getAccess_token());
    }

    @GetMapping("/cos")
    public String cos() {
        return "in cos";
    }

    @PostMapping("/noga")
    public String noga(@RequestHeader("ACCEPT") String accept) {
        System.out.println("in noga");
        System.out.println(accept);
        return accept;
    }


    @GetMapping("/categories")
    public String categories() throws JsonProcessingException {
        String resp = webClientService.httpGetRequestWitToken().get().uri("/sale/categories").retrieve().bodyToMono(String.class).block();
        return resp;
    }

    @GetMapping("/sale/offers")
    public String offersListing() {
        String resp = webClientService.httpGetRequestWitToken().get().uri("/sale/offers").retrieve().bodyToMono(String.class).block();
        return resp;
    }

    @GetMapping("/sale/delivery-settings")
    public String deliverySettings() {
        String resp = webClientService.httpGetRequestWitToken().get().uri("/sale/delivery-settings").retrieve().bodyToMono(String.class).block();
        return resp;
    }

    @GetMapping("/sale/delivery-methods")
    public String deliveryMethods() {
        String resp = webClientService.httpGetRequestWitToken().get().uri("/sale/delivery-methods").retrieve().bodyToMono(String.class).block();
        return resp;
    }
}
