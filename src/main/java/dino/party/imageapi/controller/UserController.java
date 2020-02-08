package dino.party.imageapi.controller;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity findFirstActive(@PathVariable String barcode) {
        try {
            return ResponseEntity.ok(
                    userService.findByBarcode(barcode).isActive());
        } catch (NotFound notFound) {
            return ResponseEntity.notFound().build();
        }
    }
}
