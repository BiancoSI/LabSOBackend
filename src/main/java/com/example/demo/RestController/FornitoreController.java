package com.example.demo.RestController;

import com.example.demo.Entity.Fornitore;
import com.example.demo.Service.FornitoreService;
import com.example.demo.Supports.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class FornitoreController {
    @Autowired
    private FornitoreService fs;

    @GetMapping("/admin/fornitori")
    public ResponseEntity  findAll(){
        return new ResponseEntity<>(fs.findAll(), HttpStatus.OK);
    }

    @PostMapping("/admin/add-fornitore")
    public ResponseEntity addNewFornitore(@RequestBody Fornitore f){
        Fornitore fo = null;
        try {
            fo = fs.addFornitore(f);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fo, HttpStatus.OK);
    }
    @DeleteMapping("/admin/delete/{piva}")
    public ResponseEntity deleteFornitore(@PathVariable String piva){
        fs.deleteFornitore(piva);
        return new ResponseEntity<>(new ResponseMessage("Cancellazione avvenuta con successo"), HttpStatus.OK);
    }
    @PutMapping("/admin/modify")
    public ResponseEntity modifySupplier(@RequestBody Fornitore f){
        Fornitore ret = null;
        try {
            ret = fs.modificaFornitore(f.getPiva(), f);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
