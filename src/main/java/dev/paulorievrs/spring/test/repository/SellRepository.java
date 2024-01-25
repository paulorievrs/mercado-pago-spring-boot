package dev.paulorievrs.spring.test.repository;

import dev.paulorievrs.spring.test.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {
}
