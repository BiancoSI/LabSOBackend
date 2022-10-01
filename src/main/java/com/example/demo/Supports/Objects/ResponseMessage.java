package com.example.demo.Supports.Objects;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
public class ResponseMessage implements Serializable {
    private String message;

    public ResponseMessage(String mex){
        this.message = mex;
    }
}
