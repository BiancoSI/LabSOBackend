package com.example.demo.Entity.Fornitura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter

public class PkFornitura implements Serializable {
    private String piva;
    private long id;
}
