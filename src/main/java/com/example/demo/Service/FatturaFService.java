package com.example.demo.Service;

import com.example.demo.Entity.FatturaF.FatturaFornitore;
import com.example.demo.Repository.FatturaFornitoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FatturaFService {
    @Autowired
    private FatturaFornitoreRepository ffr;

    @Transactional(readOnly = true)
    public List<FatturaFornitore> findAll(){
        return ffr.findAll();
    }
    @Transactional
    public FatturaFornitore addFattura(FatturaFornitore ff ){
        FatturaFornitore toAdd = new FatturaFornitore();
        toAdd.setData(ff.getData());
        toAdd.setOrdine(ff.getOrdine());
        toAdd.setPrezzo(ff.getPrezzo());
        toAdd.setSaldato(ff.isSaldato());
        return ffr.save(toAdd);
    }

    @Transactional
    public FatturaFornitore modificaFattura(FatturaFornitore ff) throws Exception {
        if (!ffr.existsById(ff.getId()))
            throw new Exception("Fattura Fornitore non esistente");
        FatturaFornitore toMod = ffr.getReferenceById(ff.getId());
        toMod.setSaldato(ff.isSaldato());
        return ffr.save(toMod);
    }

    @Transactional
    public void deleteFattura(long id){
        ffr.deleteById(id);
    }
}
