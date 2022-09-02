package com.example.demo.Supports.Exceptions;

public class FornitoreNonExistException extends Exception{
    private String message;

    public FornitoreNonExistException(String mex){
        super(mex);
        this.message = mex;
    }

}
