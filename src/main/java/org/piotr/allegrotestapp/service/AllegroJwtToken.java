package org.piotr.allegrotestapp.service;

import org.piotr.allegrotestapp.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class AllegroJwtToken {
    @Autowired
    private RestTemplate restTemplate;
    private String clientId = "97d2f6f663d84d968f126c15ea3ef3d0";
    private String clientSecret = "TRhxeJYV6iCKSQgeIu7XaCOPmMov1IbFiREgyEJEwCKlKotUGvYq6DVMXFfDWHXP";
    private String authCode = "";
    private Token allegroJwtToken = null;


    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public Token getAllegroJwtToken() {
        return allegroJwtToken;
    }

    public void setAllegroJwtToken(Token allegroJwtToken) {
        this.allegroJwtToken = allegroJwtToken;
    }


    public String requestJWT() {
        String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/token?grant_type=authorization_code&code=" + authCode;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        String body = "";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();

    }

    @Override
    public String toString() {
        return "AllegroJwtToken{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", authCode='" + authCode + '\'' +
                ", allegroJwtToken=" + allegroJwtToken +
                '}';
    }
}
