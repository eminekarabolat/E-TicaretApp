package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.products.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}