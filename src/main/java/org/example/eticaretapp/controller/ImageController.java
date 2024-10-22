package org.example.eticaretapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.AddImageMyProductRequestDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.example.eticaretapp.constants.RestApis.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(IMAGE)
public class ImageController {
	private final ImageService imageService;
	
	@PostMapping(ADDIMAGE)
	public ResponseEntity<BaseResponse<Boolean>> addImage(AddImageMyProductRequestDto dto){
		imageService.save(dto);
		return ResponseEntity.ok(BaseResponse.getSuccess(true, "resim kaydetme başarılı."));
	}
}