package com.example.demo.Repository;

import com.example.demo.Entity.Fornitore;
import com.example.demo.Entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    long deleteByFornitore(Fornitore fornitore);

    List<Ordine> findByFornitore_Piva(String piva);



}
