package com.example.demo.Supports.Objects;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class LogoutRequest implements Serializable {
    private String access_token;
    private String refresh_token;
}
