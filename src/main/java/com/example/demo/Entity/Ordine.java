package com.example.demo.Entity;

import com.example.demo.Entity.R_PO.R_PO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Ordine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "dataConsegna")
    private Date dataConsegna;

    @Column(name = "dataCreazione", nullable = false)
    private Date dataCreazione;

    @ManyToOne
    @JoinColumn(name = "fornitore")
    private Fornitore fornitore;

    @OneToMany(mappedBy = "ordine")
    @JsonIgnore
    private List<R_PO> ordini;
}

