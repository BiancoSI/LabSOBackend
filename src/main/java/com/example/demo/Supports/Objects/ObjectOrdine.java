package com.example.demo.Supports.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ObjectOrdine implements Serializable {
    public Date dataCreazione;
    public Date dataConsegna;
    public String piva;
    public List<helpRPO> list;
}


