package com.example.demo.Supports.Exceptions;

public class ProdottoInesistenteException extends Exception{
    private String mex;

    public ProdottoInesistenteException(String mex){
        super(mex);
        this.mex = mex;
    }
}
