package com.example.demo.RestController;

import com.example.demo.Entity.Prodotto;
import com.example.demo.Service.ProdottoService;
import com.example.demo.Supports.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProdottoController {
    @Autowired
    private ProdottoService ps;

    @GetMapping("/prodotto")
    public List<Prodotto> findAll(){
        return ps.findAll();
    }

    @PostMapping("/add-prodotto")
    public ResponseEntity addProdotto(@RequestBody Prodotto p){
        Prodotto ret = null;
        ret = ps.addProdotto(p);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @DeleteMapping("/delete-prodotto")
    public ResponseEntity deleteProdotto(@RequestParam long id){
        ps.deleteProdotto(id);
        return new ResponseEntity<>(new ResponseMessage("Cancellazione Avvenuta"), HttpStatus.OK);
    }

    @PutMapping("/modify-prodotto")
    public ResponseEntity modifyProdotto(@RequestBody Prodotto p){
        Prodotto ret = null;
        try {
            ret = ps.modificaProdotto(p);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
