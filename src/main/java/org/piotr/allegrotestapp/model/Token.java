package org.piotr.allegrotestapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Token {

    private String access_token = "";
    private String token_type = "";
    private String refresh_token = "";
    private long expires_in = 0;
    private String scope = "";
    private boolean allegro_api = false;
    private String jti = "";



}
