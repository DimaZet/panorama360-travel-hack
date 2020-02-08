package dino.party.imageapi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/display/{barcode}")
    public void displayPhotoByBarcode(
            @PathVariable("barcode") String barcode, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {

        if (StringUtils.isEmpty(barcode)) {
            throw new IllegalArgumentException("Empty barcode");
        }
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(
                imageService.findPhotosByBarcode(barcode).get(0).getImage());
        response.getOutputStream().close();
    }

}
