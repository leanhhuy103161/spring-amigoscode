package com.amigoscode.basicspring.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }
}
