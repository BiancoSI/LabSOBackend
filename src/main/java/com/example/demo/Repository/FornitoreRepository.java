package com.example.demo.Repository;

import com.example.demo.Entity.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, String> {
    List<Fornitore> findByNomeContains(String nome);

}
