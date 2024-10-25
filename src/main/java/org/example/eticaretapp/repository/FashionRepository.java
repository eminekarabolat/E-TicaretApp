package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.enums.fashion.Color;
import org.example.eticaretapp.entity.enums.fashion.FashionCategory;
import org.example.eticaretapp.entity.enums.fashion.Gender;
import org.example.eticaretapp.entity.enums.fashion.Size;
import org.example.eticaretapp.entity.products.Fashion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FashionRepository extends JpaRepository<Fashion, Long> {

    List<Fashion> findAllByGenderInAndColorInAndFashionCategoryInAndSizeInAndIdIn(List<Gender> genderList, List<Color> colorList, List<FashionCategory> fashionCategoryList, List<Size> sizeList, List<Long> ids);
}