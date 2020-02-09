package dino.party.imageapi.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.model.Background;
import dino.party.imageapi.repository.BackgroundRepository;
import org.springframework.stereotype.Service;

@Service
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

    public BackgroundService(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
    }

    public Background replaceBackground(byte[] bgBytes) {
        Background background = backgroundRepository.findAll().stream()
                .findFirst().orElse(new Background(null));
        background.setImage(bgBytes);
        return backgroundRepository.save(background);
    }

    public Background findAnyBackground() throws NotFound {
        return backgroundRepository.findAll().stream()
                .findFirst().orElseThrow(NotFound::new);
    }

    @PostConstruct
    private void init() throws IOException {
        BufferedImage b = ImageIO.read(new File("./back-china.jpeg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(b, "jpg", baos);
        replaceBackground(baos.toByteArray());
    }
}
