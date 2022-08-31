package com.example.demo.Repository;

import com.example.demo.Entity.R_PO.PkRPO;
import com.example.demo.Entity.R_PO.R_PO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottiOrdineRepository extends JpaRepository<R_PO, PkRPO> {
}
