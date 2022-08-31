package com.example.demo.Entity.Fornitura;

import com.example.demo.Entity.Fornitore;
import com.example.demo.Entity.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@ToString
@Getter
@Setter
public class Fornitura implements Serializable {
    @EmbeddedId
    private PkFornitura pk;
    @JoinColumn(name = "fornitore")
    @MapsId("piva")
    @ManyToOne
    private Fornitore fornitore;
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "prodotto")
    private Prodotto prodotto;
}
