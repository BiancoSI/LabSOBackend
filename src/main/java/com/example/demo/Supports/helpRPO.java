package com.example.demo.Supports;

import com.example.demo.Entity.Prodotto;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class helpRPO implements Serializable {
    public Prodotto prodotto;
    public double prezzo;
    public int quantita;
    
}