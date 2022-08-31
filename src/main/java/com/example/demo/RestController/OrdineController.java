package com.example.demo.RestController;

import com.example.demo.Entity.Ordine;
import com.example.demo.Service.OrdineService;
import com.example.demo.Supports.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class OrdineController {
    @Autowired
    private OrdineService os;

    @GetMapping("/admin/ordine")
    public List<Ordine> findAll(){
        return os.findAll();
    }

    @PostMapping("/admin/add-ordine")
    public ResponseEntity addProdotto(@RequestBody Ordine ord){
        Ordine o = null;
        o = os.add_Ordine(ord);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete-ordine/{id}")
    public ResponseEntity deleteOrdine(@PathVariable long id){
        os.delete_Ordine(id);
        return new ResponseEntity<>(new ResponseMessage("Ordine eliminato"), HttpStatus.OK);
    }

    @PutMapping("/admin/modify-ordine")
    public ResponseEntity modificaOrdine(@RequestBody Ordine ord){
        Ordine o = null;
        try {
            o = os.modifica_Ordine(ord);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

}
