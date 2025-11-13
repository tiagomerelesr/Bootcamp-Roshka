package com.systemform.stockusuarios.repository;

import com.systemform.stockusuarios.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
}
