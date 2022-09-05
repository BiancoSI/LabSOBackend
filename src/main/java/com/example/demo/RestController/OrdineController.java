package com.example.demo.RestController;

import com.example.demo.Entity.Ordine;
import com.example.demo.Service.OrdineService;
import com.example.demo.Supports.Exceptions.FornitoreNonExistException;
import com.example.demo.Supports.Exceptions.ProdottoInesistenteException;
import com.example.demo.Supports.ObjectOrdine;
import com.example.demo.Supports.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class OrdineController {
    @Autowired
    private OrdineService os;

    @GetMapping("/ordine")
    public ResponseEntity findAll(){
        return new ResponseEntity<>(os.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ordine/get")
    public ResponseEntity findRPOByOrdine(@RequestParam long id){
        return new ResponseEntity<>(os.getById(id), HttpStatus.OK);
    }

    @GetMapping("/ordine/get/fatture-fornitore")
    public ResponseEntity findNonFatturate(){
        return new ResponseEntity<>(os.getNonFatturate(), HttpStatus.OK);
    }

    @PostMapping("/add-ordine")
    public ResponseEntity addProdotto(@RequestBody ObjectOrdine ordine){
        Ordine o = null;
        try {
            o = os.add_Ordine(ordine);
        } catch (Exception e ) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @DeleteMapping("/delete-ordine")
    public ResponseEntity deleteOrdine(@RequestParam long id){
        os.delete_Ordine(id);
        return new ResponseEntity<>(new ResponseMessage("Ordine eliminato"), HttpStatus.OK);
    }

    @PutMapping("/modify-ordine")
    public ResponseEntity modificaOrdine(@RequestBody ObjectOrdine ord, @RequestParam long id){
        Ordine o = null;
        try {
            o = os.modifica_Ordine(ord, id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

}
