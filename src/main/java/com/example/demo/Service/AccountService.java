package com.example.demo.Service;

import com.example.demo.Configuration.RequestKeycloak;
import com.example.demo.Supports.Objects.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class AccountService {
    @Value("${keycloak.auth-server-url}")
    public String serverURL;
    @Value("${keycloak.realm}")
    public String realm;
    @Value("${keycloak.resource}")
    public String clientID;
    @Value("${keycloak.credentials.secret}")
    public String clientSecret;

    private Keycloak keycloak = new RequestKeycloak().keycloak();

    public JsonNode login(String username, String password) throws UnirestException {
        String url = serverURL+"/realms/"+realm+"/protocol/openid-connect/token";

        HttpResponse<JsonNode> http = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", clientID)
                .field("client_secret", clientSecret)
                .field("grant_type", "password")
                .field("username", username)
                .field("password", password).asJson();

        if(!http.isSuccess())
            throw new UnirestException("");
        return http.getBody();
    }

    public JsonNode logout(String refresh_token, String access_token) throws UnirestException{
        String url =  serverURL+"/realms/"+realm+"/protocol/openid-connect/logout";
        HttpResponse<JsonNode> http =  Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+access_token)
                .field("client_id", clientID)
                .field("client_secret", clientSecret)
                .field("refresh_token", refresh_token)
                .asJson();
        if(!http.isSuccess())
            throw new UnirestException("");
        return http.getBody();
    }

    public JsonNode refreshToken(String refreshToken){
        String url = serverURL+"/realms/"+realm+"/protocol/openid-connect/token";
        HttpResponse<JsonNode> http = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", clientID)
                .field("client_secret", clientSecret)
                .field("grant_type", "refresh_token")
                .field("refresh_token", refreshToken).asJson();
        if(!http.isSuccess())
            throw new UnirestException("");
        return http.getBody();
    }

    public JsonNode createUser(User user){
        UsersResource us = this.keycloak.realm(realm).users();
        CredentialRepresentation cr = setPassword(user.getPassword());

        UserRepresentation kuser = new UserRepresentation();
        kuser.setEmail(user.getEmail());
        kuser.setFirstName(user.getName());
        kuser.setLastName(user.getSurname());
        kuser.setUsername(user.getUsername());
        kuser.setCredentials(Collections.singletonList(cr));
        return (JsonNode) us.create(kuser).getEntity();
    }

    private CredentialRepresentation setPassword(String password){
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setTemporary(false);
        cr.setValue(password);
        cr.setCredentialData(CredentialRepresentation.PASSWORD);
        return cr;
    }
}
