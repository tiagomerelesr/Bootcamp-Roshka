package com.systemform.stockusuarios.controller;

import com.systemform.stockusuarios.model.Stock;
import com.systemform.stockusuarios.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired

    public StockService stockService;

    @GetMapping
    public List<Stock> listarStock() {
        return stockService.listarStock();
    }

    @PostMapping
    public Stock crearStock(@RequestBody Stock stock) {
        return stockService.guardarStock(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> actualizarStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
        Optional<Stock> updateStock = stockService.actualizarStock(id, stockDetails);
        if (updateStock.isPresent()) {
            return ResponseEntity.ok(updateStock.get());
        } else{
                return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarStock(@PathVariable Long id){
        boolean eliminado = stockService.eliminarStock(id);
        if (eliminado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

