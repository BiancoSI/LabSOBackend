package com.example.demo.RestController;

import com.example.demo.Entity.Fornitura.Fornitura;
import com.example.demo.Entity.Prodotto;
import com.example.demo.Service.FornituraService;
import com.example.demo.Supports.ArrayProdotti;
import com.example.demo.Supports.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class FornituraController {
    @Autowired
    private FornituraService fs;

    @GetMapping("/admin/forniture")
    public ResponseEntity findAll(){
        return new ResponseEntity<>(fs.findAll(), HttpStatus.OK);
    }

    @PostMapping("/admin/add-fornitura")
    public ResponseEntity addFornitura(@RequestParam String piva, @RequestBody ArrayProdotti prodotti){
        List<Fornitura> tr = null;
        try {
            tr = fs.addNewFornitura(prodotti.prodotti, piva);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tr, HttpStatus.OK);
    }

}
