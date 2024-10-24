package org.example.eticaretapp.entity.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.eticaretapp.entity.enums.fashion.Color;
import org.example.eticaretapp.entity.enums.fashion.FashionCategory;
import org.example.eticaretapp.entity.enums.fashion.Gender;
import org.example.eticaretapp.entity.enums.fashion.Size;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblfashion")
public class Fashion extends Product{
	@Enumerated(EnumType.STRING)
	FashionCategory fashionCategory;
	@Enumerated(EnumType.STRING)
	Color color;
	@Enumerated(EnumType.STRING)
	Gender gender;
	@Enumerated(EnumType.STRING)
	Size size;
}