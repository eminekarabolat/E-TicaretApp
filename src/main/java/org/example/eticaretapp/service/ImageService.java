package org.example.eticaretapp.service;

import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.mapper.ImageMapper;
import org.example.eticaretapp.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	private final ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	public void save(AddImageMyProductRequestDto dto) {
		Image image = ImageMapper.INSTANCE.fromAddImageProductDto(dto);
		imageRepository.save(image);
	}
}