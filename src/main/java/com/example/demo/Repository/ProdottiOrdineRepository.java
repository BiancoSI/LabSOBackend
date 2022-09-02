package com.example.demo.Repository;

import com.example.demo.Entity.R_PO.PkRPO;
import com.example.demo.Entity.R_PO.R_PO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottiOrdineRepository extends JpaRepository<R_PO, PkRPO> {
    long deleteByPk_IdOrdine(long idOrdine);

    List<R_PO> findByPk_IdOrdine(long idOrdine);

    @Query(value = "Select r.* " +
            "From R_PO as r, Ordine as o " +
            "Where o.id NOT IN (Select f1.ordine From fattura_fornitore as f1) " +
            "and o.id = r.ordine", nativeQuery = true)
    List<R_PO> findNonFatturate();


}
