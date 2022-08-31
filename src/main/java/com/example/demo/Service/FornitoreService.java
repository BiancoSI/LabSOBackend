package com.example.demo.Service;

import com.example.demo.Entity.Fornitore;

import com.example.demo.Repository.FornitoreRepository;
import com.example.demo.Repository.FornituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornitoreService {
    @Autowired
    public FornitoreRepository fr;

    @Autowired
    public FornituraRepository ffr;

    @Transactional(readOnly = true)
    public List<Fornitore> findAll(){
        return fr.findAll();
    }

    @Transactional
    public Fornitore addFornitore(Fornitore f) throws Exception {
        if ( f.getPiva().length()!=11 || !f.getPiva().matches("\\d+") || fr.existsById(f.getPiva()) )
            throw new Exception("Fornitore già esistente.");
        return fr.save(f);
    }

    @Transactional
    public Fornitore modificaFornitore(String piva, Fornitore f) throws Exception {
        if (!fr.existsById(piva) || !piva.equals(f.getPiva()))
            throw new Exception("Fornitore non esistente");
        Fornitore modified = fr.getReferenceById(piva);
        modified.setNome(f.getNome());
        modified.setRecapito(f.getRecapito());
        modified.setSede(f.getSede());
        return fr.save(modified);
    }

    @Transactional
    public void deleteFornitore(String piva) {
        fr.deleteById(piva);
    }

    @Transactional(readOnly = true)
    public List<Fornitore> findByName(String name){
        return fr.findByNomeContains(name);
    }
}
