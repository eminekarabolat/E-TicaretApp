package org.example.eticaretapp.service;

import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.dto.request.FindPetProductRequestDto;
import org.example.eticaretapp.entity.products.PetProduct;
import org.example.eticaretapp.entity.products.Product;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.PetProductMapper;
import org.example.eticaretapp.mapper.ProductMapper;
import org.example.eticaretapp.repository.PetProductRepository;
import org.example.eticaretapp.view.Product.VwPetProduct;
import org.example.eticaretapp.view.VwProducts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetProductService {
    private final PetProductRepository petProductRepository;

    public List<VwProducts> findPetProduct(FindPetProductRequestDto dto, List<Product> productList) {
        List<Long> ids = productList.stream().map(product -> product.getId()).toList();
        List<PetProduct> petProductList = petProductRepository
                .findAllByAnimalTypeInAndProductTypeInAndIdIn(dto.animalTypeList()
                        , dto.productTypeList(),ids);

        List<VwProducts> vwProductsList = new ArrayList<>();
        petProductList.forEach(petProduct -> {
            VwProducts vwProducts = ProductMapper.INSTANCE.fromFindPetProductRequestDto(petProduct);
            vwProductsList.add(vwProducts);
        });
        return vwProductsList;
    }

    public VwPetProduct findById(Long productId) {
        Optional<PetProduct> optionalPetProduct = petProductRepository.findById(productId);
        if(optionalPetProduct.isEmpty())
            throw new ETicaretException(ErrorType.NOTFOUND_PETPRODUCT);
        VwPetProduct vwPetProduct = PetProductMapper.INSTANCE.fromViewPetproduct(optionalPetProduct.get());
        return vwPetProduct;
    }
}