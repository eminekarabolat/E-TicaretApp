package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.FindFashionRequestDto;
import org.example.eticaretapp.entity.products.Fashion;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.FashionMapper;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.FashionRepository;
import org.example.eticaretapp.repository.ProductRepository;
import org.example.eticaretapp.view.Product.VwFashion;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FashionService {
    private final FashionRepository fashionRepository;
    private final ProductRepository productRepository;

    public List<VwProducts> findFashion(FindFashionRequestDto dto, List<Long> ids) {
        List<Fashion> fashionList = fashionRepository
                .findAllByGenderInAndColorInAndFashionCategoryInAndSizeInAndIdIn(dto.genderList()
                        , dto.colorList(), dto.fashionCategoryList(), dto.sizeList(),ids);

        List<VwProducts> vwProductsList = new ArrayList<>();
        fashionList.forEach(fashion -> {
            VwProducts vwProducts = ProductMapper.INSTANCE.fromFindFashionRequestDto(fashion);
            vwProductsList.add(vwProducts);
        });
        return vwProductsList;

    }

    public VwFashion findById(Long productId) {
        Optional<Fashion> optionalFashion = fashionRepository.findById(productId);
        if(optionalFashion.isEmpty())
            throw new ETicaretException(ErrorType.NOTFOUND_FASHION);

        VwFashion vwFashion = FashionMapper.INSTANCE.fromViewFashion(optionalFashion.get());
        return vwFashion;

    }
}