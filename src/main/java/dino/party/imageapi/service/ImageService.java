package dino.party.imageapi.service;

import java.io.IOException;
import java.util.List;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.model.Image;
import dino.party.imageapi.repository.BackgroundRepository;
import dino.party.imageapi.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final BackgroundCutter backgroundCutter;
    private final ImageRepository imageRepository;
    private final BackgroundRepository backgroundRepository;

    public ImageService(BackgroundCutter backgroundCutter, ImageRepository imageRepository,
                        BackgroundRepository backgroundRepository) {
        this.backgroundCutter = backgroundCutter;
        this.imageRepository = imageRepository;
        this.backgroundRepository = backgroundRepository;
    }

    public Image uploadPhoto(String barcode, byte[] photo) throws IOException, NotFound {
        byte[] background = backgroundRepository.findAll().stream()
                .findFirst().orElseThrow(NotFound::new).getImage();
        byte[] editedPhoto = backgroundCutter.chromaToBackground(photo, background, "png");
        return imageRepository.save(new Image(barcode, photo, editedPhoto));
    }

    public List<Image> findPhotosByBarcode(String barcode) {
        return imageRepository.findAllByBarcode(barcode);
    }
}
