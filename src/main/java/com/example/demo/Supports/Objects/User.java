package com.example.demo.Supports.Objects;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class User implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
