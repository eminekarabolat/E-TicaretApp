package org.example.eticaretapp.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eticaretapp.entity.Image;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwProducts {
	Long id;
	String name;
	Double price;
	List<Image> imageList;
}