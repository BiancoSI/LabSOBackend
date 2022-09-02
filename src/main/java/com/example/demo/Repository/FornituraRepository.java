package com.example.demo.Repository;

import com.example.demo.Entity.Fornitura.Fornitura;
import com.example.demo.Entity.Fornitura.PkFornitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornituraRepository extends JpaRepository<Fornitura, PkFornitura> {
    long deleteByPk_Piva(String piva);

    long deleteByPk_Id(long id);

    List<Fornitura> findByPk_Piva(String piva);




}
