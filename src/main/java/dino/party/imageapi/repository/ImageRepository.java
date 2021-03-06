package dino.party.imageapi.repository;

import java.util.List;
import java.util.Optional;

import dino.party.imageapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByBarcode(String barcode);
    Optional<Image> findByBarcode(String barcode);
}
