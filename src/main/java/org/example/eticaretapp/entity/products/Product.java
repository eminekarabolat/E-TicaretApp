package org.example.eticaretapp.entity.products;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.BaseEntity;
import org.example.eticaretapp.entity.enums.Status;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblproduct")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long sellerId;
	String brand;
	String name;
	String description;
	Double price;
	Integer stockQuantity;
	@Builder.Default
	String sku = UUID.randomUUID().toString();
	@Builder.Default
	Status status = Status.ACTIVE;
	
}