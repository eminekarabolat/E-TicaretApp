package org.example.eticaretapp.dto.request;


import org.example.eticaretapp.entity.enums.fashion.Color;
import org.example.eticaretapp.entity.enums.fashion.FashionCategory;
import org.example.eticaretapp.entity.enums.fashion.Gender;
import org.example.eticaretapp.entity.enums.fashion.Size;

import java.util.List;

public record FindFashionRequestDto(
        List<FashionCategory> fashionCategoryList,
        List<Color> colorList,
        List<Gender> genderList,
        List<Size> sizeList
) {
}
