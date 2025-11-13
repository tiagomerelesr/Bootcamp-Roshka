package com.systemform.stockusuarios.service;

import com.systemform.stockusuarios.model.Stock;
import com.systemform.stockusuarios.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.systemform.stockusuarios.repository.UsuarioRepository;
import com.systemform.stockusuarios.model.Usuario;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public List<Stock> listarStock(){
        return stockRepository.findAll();
    }

    public Stock guardarStock(Stock stock){
        return stockRepository.save(stock);
    }
    // Actualizar producto
    public Optional<Stock> actualizarStock(Long id, Stock stockDetails) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            stock.setNombre(stockDetails.getNombre());
            stock.setCantidad(stockDetails.getCantidad());
            stockRepository.save(stock);
        }
        return optionalStock;
    }

    // Eliminar producto
    public boolean eliminarStock(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Optional<Stock> asignarUsuario(Long stockId, Long usuarioId) {
        Optional<Stock> stockOpt = stockRepository.findById(stockId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (stockOpt.isPresent() && usuarioOpt.isPresent()) {
            Stock stock = stockOpt.get();
            Usuario usuario = usuarioOpt.get();

            stock.setUsuario(usuario);
            return Optional.of(stockRepository.save(stock));
        }

        return Optional.empty();
    }
}

