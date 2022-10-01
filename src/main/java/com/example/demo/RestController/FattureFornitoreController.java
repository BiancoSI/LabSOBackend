package com.example.demo.RestController;

import com.example.demo.Entity.FatturaF.FatturaFornitore;
import com.example.demo.Service.FatturaFService;
import com.example.demo.Supports.Objects.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class FattureFornitoreController {
    @Autowired
    private FatturaFService fs;

    @GetMapping("/fatture-fornitore")
    public List<FatturaFornitore> findAll(){
        return fs.findAll();
    }

    @PostMapping("/add-fatture-fornitore")
    public ResponseEntity addFattura(@RequestBody FatturaFornitore f){
        FatturaFornitore ff = fs .addFattura(f);
        return new ResponseEntity<>(ff, HttpStatus.OK);
    }

    @DeleteMapping("/delete-fatture-fornitore")
    public ResponseEntity deleteFatture(@RequestParam long id){
        fs.deleteFattura(id);
        return new ResponseEntity<>(new ResponseMessage("Rimozione Avvenuta"), HttpStatus.OK);
    }

    @PutMapping("/modify-fatture-fornitore")
    public ResponseEntity modifyFatture(@RequestBody FatturaFornitore ff){
        FatturaFornitore ret =null;
        try {
            ret = fs.modificaFattura(ff);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
