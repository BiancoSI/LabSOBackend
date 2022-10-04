package com.example.demo.RestController;

import com.auth0.jwt.JWT;
import com.example.demo.Service.AccountService;
import com.example.demo.Supports.Objects.*;
import kong.unirest.JsonNode;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    public AccountService as;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest login){

        JsonNode response = null;
        ResponseLogin rl;
        try{
            response = as.login(login.getUsername(), login.getPassword());
            rl = new ResponseLogin((String)response.getObject().get("access_token"), (String)response.getObject().get("refresh_token"));
            rl.calcolaTutto();
        }catch(UnirestException e){
            return new ResponseEntity(new ResponseMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(rl, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken(@RequestBody LogoutRequest refresh_token){
        JsonNode response=null;
        ResponseLogin rl;
        try{
            response = as.refreshToken(refresh_token.getRefresh_token());
            System.out.println(response.getObject());
            rl = new ResponseLogin((String)response.getObject().get("access_token"), (String)response.getObject().get("refresh_token"));
            rl.calcolaTutto();
        }catch(UnirestException e){
            return new ResponseEntity(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rl, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutRequest lr){
        try {
            System.out.println(as.logout( lr.getRefresh_token(), lr.getAccess_token() ) . getObject());
        }catch(UnirestException e){
            return new ResponseEntity<>(new ResponseMessage("Errore nel Logout: "+e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseMessage("Logout effettuato con successo"), HttpStatus.OK);
    }

    @PostMapping("/register/new-user")
    public ResponseEntity add_user(@RequestBody User user){

        try{
            as.createUser(user);
        }catch(UnirestException e){
            return new ResponseEntity(new ResponseMessage("Errore nella registrazione dell'utente "), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Creazione Avvenuta", HttpStatus.OK);
    }

}
