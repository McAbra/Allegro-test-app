package org.piotr.allegrotestapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.piotr.allegrotestapp.model.Token;
import org.piotr.allegrotestapp.service.AllegroJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private AllegroJwtToken allegroJwtToken;


    @RequestMapping("/home")
    public void returnMessage(@RequestParam String code) throws JsonProcessingException {
        allegroJwtToken.setAuthCode(code);
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.readValue(allegroJwtToken.requestJWT(), Token.class);
        allegroJwtToken.setAllegroJwtToken(token);
        System.out.println(allegroJwtToken.getAllegroJwtToken().getAccess_token());
    }

    @GetMapping("/noga")
    public String noga() {
        return "noga";
    }
}
