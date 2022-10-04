package com.example.demo.Configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RequestKeycloak {

    private static RestTemplate restTemplate = new RestTemplate();

    private static String clientId = "login-app";

    private static String authUrl = "http://localhost:8080/";

    private static String realm = "serverbb";
    private static String secret = "7wVAMswcLLguzGTPW3zCQV0hOIjIud1P";

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(secret)
                .username("admin_realm@keycloak.key")
                .password("password")
                .build();
    }

    public static boolean verify_password(String username, String pass) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("client_id", clientId);
        params.add("username", username);
        params.add("password", pass);
        params.add("grant_type", "password");

        HttpEntity<?> request = new HttpEntity<Object>(params, header);
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(
                    "http://localhost:8080/auth/realms/SpringBootKeycloak/protocol/openid-connect/token",
                    HttpMethod.POST, request, String.class);
            return !response.getStatusCode().is4xxClientError() || !response.getStatusCode().is5xxServerError();
        } catch (Exception e) {
            return false;
        }
    }
}

