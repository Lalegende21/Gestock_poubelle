package com.gestock.repositories;

import com.gestock.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    void deleteImageByName(String name);
}
