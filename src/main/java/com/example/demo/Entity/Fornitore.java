package com.example.demo.Entity;

import com.example.demo.Entity.Fornitura.Fornitura;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Fornitore implements Serializable {
    @Id
    @Column(name = "piva", nullable = false, length = 11)
    private String piva;

    @Column(name = "nome")
    private String nome;

    @Column(name = "recapito", unique = true)
    private String recapito;

    @Column(name = "sede")
    private String sede;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "fornitore")
    private List<Fornitura> listaProdotti;

    @OneToMany(mappedBy = "fornitore")
    @JsonIgnore
    @ToString.Exclude
    private List<Ordine> ordini;

}
