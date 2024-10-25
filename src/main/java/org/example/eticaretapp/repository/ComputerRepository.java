package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.enums.computer.ComputerType;
import org.example.eticaretapp.entity.enums.computer.Harddisk;
import org.example.eticaretapp.entity.enums.computer.Ram;
import org.example.eticaretapp.entity.products.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
	// @Query("SELECT * FROM Computer c WHERE c.ram IN ?1 AND c.id...")
	List<Computer> findAllByRamInAndHarddiskInAndComputerTypeInAndIdIn(List<Ram> rams, List<Harddisk> harddisks, List<ComputerType> computerTypes, List<Long> ids);


}