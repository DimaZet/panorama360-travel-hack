package dino.party.imageapi.service;

import java.io.IOException;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.model.Image;
import dino.party.imageapi.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final BackgroundCutter backgroundCutter;
    private final ImageRepository imageRepository;
    private final BackgroundService backgroundService;

    public ImageService(BackgroundCutter backgroundCutter, ImageRepository imageRepository,
                        BackgroundService backgroundService) {
        this.backgroundCutter = backgroundCutter;
        this.imageRepository = imageRepository;
        this.backgroundService = backgroundService;
    }

    public Image uploadPhoto(String barcode, byte[] photo) throws IOException, NotFound {
        Image image = imageRepository.findByBarcode(barcode)
                .orElse(new Image(barcode, photo, null));
        byte[] background = backgroundService.findAnyBackground().getImage();
        image.setEditedImage(backgroundCutter.chromaToBackground(photo, background, "png"));
        return imageRepository.save(image);
    }

    public Image findPhotosByBarcode(String barcode) throws NotFound {
        return imageRepository.findByBarcode(barcode).orElseThrow(NotFound::new);
    }
}
