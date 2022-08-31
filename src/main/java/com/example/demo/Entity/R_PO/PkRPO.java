package com.example.demo.Entity.R_PO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PkRPO implements Serializable {
    private long idOrdine;
    private long idProdotto;
}
