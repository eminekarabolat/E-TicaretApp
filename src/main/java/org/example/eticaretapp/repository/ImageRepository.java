package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}