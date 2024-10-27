package org.example.eticaretapp.service;

import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.ImageMapper;
import org.example.eticaretapp.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {
	private final ImageRepository imageRepository;
	private final CloudinaryService cloudinaryService;
	
	public ImageService(ImageRepository imageRepository, CloudinaryService cloudinaryService) {
		this.imageRepository = imageRepository;
		this.cloudinaryService = cloudinaryService;
	}
	
	public void save(AddImageMyProductRequestDto dto) {
		Image image = ImageMapper.INSTANCE.fromAddImageProductDto(dto);
		imageRepository.save(image);
	}

	public String saveImage(Long productId, MultipartFile file) throws IOException {
		// Dosya boyutunu kontrol et
		if (file.getSize() > 5 * 1024 * 1024) {
			throw new ETicaretException(ErrorType.IMAGE_SIZE_ERROR);
		}
		
		// Dosyayı byte array'e çevir
		byte[] imageBytes = file.getBytes();
		
		// Cloudinary veya başka bir servise resmi yükle
		Map uploadResult = cloudinaryService.uploadImage(imageBytes);
		
		// Yüklenen resmin URL'sini al
		String uploadedImageUrl = uploadResult.get("url").toString();
		
		Image image = Image.builder()
				.productId(productId)
				.url(uploadedImageUrl)
				.build();
		imageRepository.save(image);
		return image.getUrl();
	}

	public List<Image> findAll(){
		return imageRepository.findAll();
	}

	public List<Image> findAllByProductId(Long id) {
		return imageRepository.findAllByProductId(id);
	}
}