package com.example.demo.Service;

import com.example.demo.Entity.Fornitura.Fornitura;
import com.example.demo.Entity.Fornitura.PkFornitura;
import com.example.demo.Entity.Prodotto;
import com.example.demo.Repository.FornitoreRepository;
import com.example.demo.Repository.FornituraRepository;
import com.example.demo.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class FornituraService {
    @Autowired
    private ProdottoRepository pr;
    @Autowired
    private FornitoreRepository fr;
    @Autowired
    private FornituraRepository ffr;

    @Transactional(readOnly = true)
    public List<Fornitura> findAll(){
        return ffr.findAll();
    }

    @Transactional
    public List<Fornitura>  addNewFornitura(List<Prodotto> list, String piva) throws Exception {
        if(!fr.existsById(piva))
            throw new Exception("Fornitore non esistente");
        List<Fornitura> fornitura = new LinkedList<>();
        for(Prodotto p : list){
            PkFornitura pk = new PkFornitura();
            pk.setId(p.getId()); pk.setPiva(piva);
            if(!pr.existsById(p.getId()))
                throw new Exception("Prodotto Non esistente");
            Fornitura f = new Fornitura();
            f.setPk(pk);
            f.setFornitore(fr.getReferenceById(piva));
            f.setProdotto(pr.getReferenceById(p.getId()));
            fornitura.add(f);
        }
        ffr.saveAll(fornitura);
        return fornitura;
    }

    @Transactional
    public void deleteFornitura (List<Prodotto> list, String piva){
        for(Prodotto p:list){
            PkFornitura pk = new PkFornitura();
            pk.setPiva(piva);
            pk.setId(p.getId());
            ffr.deleteById(pk);
        }
    }
}
