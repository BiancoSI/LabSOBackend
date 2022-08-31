package com.example.demo.Entity.FatturaF;


import com.example.demo.Entity.Ordine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class FatturaFornitore implements Serializable {
    @Id
    private long Id;

    @Column(name = "data")
    private Date data;

    @Column(name = "saldato")
    private boolean saldato;

    @Column(name = "prezzo")
    private double prezzo;

    @ManyToOne
    @JoinColumn(name = "ordine")
    private Ordine ordine;

}
