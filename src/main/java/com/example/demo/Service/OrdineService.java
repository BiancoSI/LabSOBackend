package com.example.demo.Service;

import com.example.demo.Entity.Ordine;
import com.example.demo.Entity.R_PO.PkRPO;
import com.example.demo.Entity.R_PO.R_PO;
import com.example.demo.Repository.OrdineRepository;
import com.example.demo.Repository.ProdottiOrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository or;
    @Autowired
    private ProdottiOrdineRepository rpo;

    @Transactional(readOnly = true)
    public List<Ordine> findAll(){
        return or.findAll();
    }
    @Transactional
    public Ordine add_Ordine(Ordine o){
        Ordine toAdd =  new Ordine();
        toAdd.setFornitore(o.getFornitore());
        toAdd.setDataConsegna(o.getDataConsegna());
        toAdd.setDataCreazione(o.getDataCreazione());
        toAdd.setOrdini(o.getOrdini());
        for(R_PO ord : o.getOrdini()){
            rpo.save(ord);
        }
        return or.save(o);
    }
    @Transactional
    public void delete_Ordine(long id){
        or.deleteById(id);
    }

    @Transactional
    public Ordine modifica_Ordine(Ordine o) throws Exception {
        if ( !or.existsById(o.getId()))
            throw new Exception("Ordine inesistente");
        Ordine or = this.or.getReferenceById(o.getId());
        or.setOrdini(o.getOrdini());
        return this.or.save(or);
    }
}
