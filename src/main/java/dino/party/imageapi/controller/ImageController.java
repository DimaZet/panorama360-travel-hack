package dino.party.imageapi.controller;

import java.io.IOException;

import dino.party.imageapi.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/photos")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity uploadPhoto(
            @RequestParam("barcode") String barcode,
            @RequestParam("photo") MultipartFile photo
            ) throws IOException {
        if (StringUtils.isEmpty(barcode)) {
            throw new IllegalArgumentException("Empty barcode");
        }
        return ResponseEntity.ok(
                imageService.uploadPhoto(barcode, photo.getBytes())
        );
    }

    @GetMapping("/{barcode}")
    public ResponseEntity findPhotosByBarcode(
            @PathVariable("barcode") String barcode
    ) {
        if (StringUtils.isEmpty(barcode)) {
            throw new IllegalArgumentException("Empty barcode");
        }
        return ResponseEntity.ok(
                imageService.findPhotosByBarcode(barcode)
        );
    }
}
