package com.example.demo.Repository;

import com.example.demo.Entity.FatturaF.FatturaFornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaFornitoreRepository extends JpaRepository<FatturaFornitore, Long> {
}
