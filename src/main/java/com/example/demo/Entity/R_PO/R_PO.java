package com.example.demo.Entity.R_PO;

import com.example.demo.Entity.Ordine;
import com.example.demo.Entity.Prodotto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class R_PO implements Serializable {
    @EmbeddedId
    private PkRPO pk;

    @ManyToOne
    @MapsId("idProdotto")
    @JoinColumn(name="prodotto")
    private Prodotto prodotto;

    @ManyToOne
    @MapsId("idOrdine")
    @JoinColumn(name = "ordine")
    private Ordine ordine;

    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "quantita")
    private int quantita;
}
