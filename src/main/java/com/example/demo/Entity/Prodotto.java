package com.example.demo.Entity;

import com.example.demo.Entity.Fornitura.Fornitura;
import com.example.demo.Entity.R_PO.R_PO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
public class Prodotto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "quantita")
    private int quantita;
    @Column(name = "tipologia")
    private String tipologia;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    @ToString.Exclude
    private List<Fornitura> fornitori;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    @ToString.Exclude
    private List<R_PO> ordini;

    @Version
    @JsonIgnore
    @ToString.Exclude
    private long version;
}
