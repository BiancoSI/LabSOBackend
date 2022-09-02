package com.example.demo.Service;

import com.example.demo.Entity.Prodotto;
import com.example.demo.Repository.FornituraRepository;
import com.example.demo.Repository.ProdottiOrdineRepository;
import com.example.demo.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository pr;
    @Autowired
    public FornituraRepository fornituraRepository;
    @Autowired
    private ProdottiOrdineRepository rpo;

    @Transactional(readOnly = true)
    public List<Prodotto> findAll(){
        return pr.findAll();
    }

    @Transactional
    public void deleteProdotto(long id){
        fornituraRepository.deleteByPk_Id(id);
        pr.deleteById(id);
    }

    @Transactional
    public Prodotto addProdotto(Prodotto p){
        Prodotto toAdd = new Prodotto();
        toAdd.setNome(p.getNome());
        toAdd.setQuantita(p.getQuantita());
        toAdd.setTipologia(p.getTipologia());
        return pr.save(toAdd);
    }
    @Transactional
    public Prodotto modificaProdotto(Prodotto p) throws Exception {
        if(!pr.existsById(p.getId()))
            throw new Exception("Prodotto non esistente");
        Prodotto toMod = pr.getReferenceById(p.getId());
        toMod.setNome(p.getNome());
        toMod.setTipologia(p.getTipologia());
        toMod.setQuantita(p.getQuantita());
        return pr.save(p);
    }
}
