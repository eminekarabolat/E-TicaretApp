package org.example.eticaretapp.view;

import lombok.Builder;
import org.example.eticaretapp.entity.Image;
import org.example.eticaretapp.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public class VwProductDetail {

    Long id;
    String brand;
    String name;
    String description;
    Double price;
    Integer stockQuantity;
    String sku = UUID.randomUUID().toString();
    Status status = Status.ACTIVE;
    List<Image> imageList;
}
