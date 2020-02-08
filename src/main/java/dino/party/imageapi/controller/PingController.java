package dino.party.imageapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("ping successfully");
    }
}
