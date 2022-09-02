package com.example.demo.Service;

import com.example.demo.Entity.Fornitore;
import com.example.demo.Entity.Ordine;
import com.example.demo.Entity.Prodotto;
import com.example.demo.Entity.R_PO.PkRPO;
import com.example.demo.Entity.R_PO.R_PO;
import com.example.demo.Repository.FornitoreRepository;
import com.example.demo.Repository.OrdineRepository;
import com.example.demo.Repository.ProdottiOrdineRepository;
import com.example.demo.Repository.ProdottoRepository;
import com.example.demo.Supports.Exceptions.FornitoreNonExistException;
import com.example.demo.Supports.Exceptions.ProdottoInesistenteException;
import com.example.demo.Supports.ObjectOrdine;
import com.example.demo.Supports.helpRPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository or;
    @Autowired
    private ProdottiOrdineRepository rpo;

    @Autowired
    private FornitoreRepository fr;

    @Autowired
    private ProdottoRepository pr;

    @Transactional(readOnly = true)
    public List<Ordine> findAll(){
        return or.findAll();
    }
    @Transactional
    public Ordine add_Ordine(ObjectOrdine o) throws FornitoreNonExistException, ProdottoInesistenteException {
        if(!fr.existsById(o.piva))
            throw new FornitoreNonExistException("fornitore inesistente");
        Fornitore f = fr.getReferenceById(o.piva);
        Ordine justAdded = null;

        Ordine toAdd =  new Ordine();
        toAdd.setFornitore(f);
        toAdd.setDataConsegna(o.dataConsegna);
        toAdd.setDataCreazione(o.dataCreazione);
        justAdded = or.save(toAdd);
        f.getOrdini().add(justAdded);
        List<R_PO> list = new LinkedList<>();
        for(helpRPO p : o.list) {
            if (!pr.existsById(p.prodotto.getId()))
                throw new ProdottoInesistenteException("Prodotto inesistente con Id " + p.prodotto.getId());
            PkRPO pk = new PkRPO();
            pk.setIdOrdine(justAdded.getId());
            pk.setIdProdotto(p.prodotto.getId());
            R_PO rpo = new R_PO();
            rpo.setPk(pk);
            rpo.setPrezzo(p.prezzo);
            rpo.setProdotto(p.prodotto);
            rpo.setQuantita(p.quantita);
            rpo.setOrdine(justAdded);
            list.add(rpo);
        }
        rpo.saveAll(list);
        return or.save(justAdded);
    }

    @Transactional(readOnly = true)
    public List<R_PO> getById(long id){
        return rpo.findByPk_IdOrdine(id);
    }
    @Transactional
    public void delete_Ordine(long id){
        rpo.deleteByPk_IdOrdine(id);
        or.deleteById(id);
    }

    public List<R_PO> getNonFatturate(){
        return rpo.findNonFatturate();
    }

    @Transactional
    public Ordine modifica_Ordine(ObjectOrdine o, long id) throws Exception {
        if ( !or.existsById(id))
            throw new Exception("Ordine inesistente");
        Ordine or = this.or.getReferenceById(id);
        or.setDataConsegna(o.getDataConsegna());
        or.setDataCreazione(o.getDataCreazione());
        if(!or.getFornitore().getPiva().equals(o.piva)) {
            Fornitore f = fr.getReferenceById(or.getFornitore().getPiva());
            f.getOrdini().remove(or);
            or.setFornitore(fr.getReferenceById(o.piva));
            f = fr.getReferenceById(or.getFornitore().getPiva());
            f.getOrdini().add(or);
        }
        List<R_PO> list = new LinkedList<>();

        rpo.deleteByPk_IdOrdine(id);
        for(helpRPO p : o.list) {
            if (!pr.existsById(p.prodotto.getId()))
                throw new ProdottoInesistenteException("Prodotto inesistente con Id " + p.prodotto.getId());
            PkRPO pk = new PkRPO();
            pk.setIdOrdine(or.getId());
            pk.setIdProdotto(p.prodotto.getId());
            R_PO rpo = new R_PO();
            rpo.setPk(pk);
            rpo.setPrezzo(p.prezzo);
            rpo.setProdotto(p.prodotto);
            rpo.setQuantita(p.quantita);
            rpo.setOrdine(or);
            list.add(rpo);
        }
        rpo.saveAll(list);
        return this.or.save(or);
    }
}
