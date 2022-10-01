package com.example.demo.Supports.Objects;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


import java.io.Serializable;
import java.util.*;

@Getter
@ToString
public class ResponseLogin implements Serializable {
    private String access_token;
    private String refresh_token;
    private List<String> roles;
    private String username;

    public ResponseLogin(String ac_t, String refresh_token){
        this.access_token = ac_t; this.refresh_token = refresh_token;
    }

    public void calcolaTutto(){
        DecodedJWT jwt = JWT.decode(access_token);
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(jwt.getPayload()));
        JSONObject json = new JSONObject(payload);
        JSONArray array_roles = json.getJSONObject("realm_access").getJSONArray("roles");
        username = json.get("preferred_username").toString();
        roles = new LinkedList<>();
        Iterator<String> it = array_roles.toList().iterator();
        while(it.hasNext())
            roles.add(it.next());
    }
}
