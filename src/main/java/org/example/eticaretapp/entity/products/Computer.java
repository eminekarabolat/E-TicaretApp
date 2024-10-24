package org.example.eticaretapp.entity.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.enums.computer.ComputerType;
import org.example.eticaretapp.entity.enums.computer.Harddisk;
import org.example.eticaretapp.entity.enums.computer.Ram;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblcomputer")
public class Computer  extends  Product{
	@Enumerated(EnumType.STRING)
	ComputerType computerType;
	@Enumerated(EnumType.STRING)
	Harddisk harddisk;
	@Enumerated(EnumType.STRING)
	Ram ram;
}