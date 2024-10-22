package org.example.eticaretapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.service.CloudinaryService;
import org.example.eticaretapp.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.example.eticaretapp.constants.RestApis.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(IMAGE)
public class ImageController {
	private final ImageService imageService;
	private final CloudinaryService cloudinaryService;

	@PostMapping(ADDIMAGE)
	public ResponseEntity<BaseResponse<Boolean>> addImage(AddImageMyProductRequestDto dto){
		imageService.save(dto);
		return ResponseEntity.ok(BaseResponse.getSuccess(true, "resim kaydetme başarılı."));
	}


	@PostMapping(value = UPLOADPHOTO, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadPhotoByUrl(@RequestParam("file") MultipartFile file,
												   @RequestParam("productId") Long productId) throws IOException {

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

		// Image sınıfında productId ile kaydet
		imageService.saveImage(productId, uploadedImageUrl);

		return ResponseEntity.ok("Resim başarıyla yüklendi. URL: " + uploadedImageUrl);

	}
	@GetMapping(FINDALL)
	public ResponseEntity<BaseResponse<List<Image>>> findAll(){
		return ResponseEntity.ok(BaseResponse.getSuccess(imageService.findAll()));
	}
}