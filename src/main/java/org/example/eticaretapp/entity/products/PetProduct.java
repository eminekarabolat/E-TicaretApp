package org.example.eticaretapp.entity.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.enums.petProducts.AnimalType;
import org.example.eticaretapp.entity.enums.petProducts.ProductType;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblpetproduct")
@PrimaryKeyJoinColumn(name = "id")
public class PetProduct extends Product {
	@Enumerated(EnumType.STRING)
	AnimalType animalType;
	@Enumerated(EnumType.STRING)
	ProductType productType;

}