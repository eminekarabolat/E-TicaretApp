package org.example.eticaretapp.service;

import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ImageMapper;
import org.example.eticaretapp.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

	public Image saveImage(Long productId, String imageUrl) {
		Image image = Image.builder()
				.productId(productId)
				.url(imageUrl)
				.build();
		return imageRepository.save(image);
	}

	public List<Image> findAll(){
		return imageRepository.findAll();
	}

	public List<Image> findAllByProductId(Long id) {
		return imageRepository.findAllByProductId(id);
	}
}