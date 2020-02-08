package dino.party.imageapi.service;

import java.util.List;

import dino.party.imageapi.model.Image;
import dino.party.imageapi.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image uploadPhoto(String barcode, byte[] photo) {
        return imageRepository.save(new Image(barcode, photo));
    }

    public List<Image> findPhotosByBarcode(String barcode) {
        return imageRepository.findAllByBarcode(barcode);
    }
}
