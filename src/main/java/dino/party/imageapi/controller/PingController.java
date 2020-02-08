package dino.party.imageapi.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api("ping")
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.ok("ping successfully");
    }

    @GetMapping
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }

}
