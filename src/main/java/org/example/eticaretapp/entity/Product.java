package org.example.eticaretapp.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.enums.Category;
import org.example.eticaretapp.entity.enums.Status;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblproduct")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long sellerId;
	@Enumerated(EnumType.STRING)
	Category category;
	String brand;
	String name;
	String description;
	Double price;
	Integer stockQuantity;
	String sku;
	Status status;
	Float weight;
	
}