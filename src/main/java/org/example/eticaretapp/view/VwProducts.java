package org.example.eticaretapp.view;

import org.example.eticaretapp.entity.Image;

import java.util.List;


public record VwProducts(
		Long id,
		String name,
		Double price,
		List<Image> imageList
		
) {

}