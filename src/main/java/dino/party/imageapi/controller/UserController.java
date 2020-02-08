package dino.party.imageapi.controller;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{barcode}/isActive")
    public ResponseEntity isUserActive(@PathVariable String barcode) {
        try {
            return ResponseEntity.ok(
                    userService.isUserActive(barcode));
        } catch (NotFound notFound) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestParam(name = "barcode") String barcode) {
        return ResponseEntity.ok(
                userService.createUser(barcode));
    }

    @DeleteMapping
    public ResponseEntity deactivateUser(@RequestParam(name = "barcode") String barcode) {
        try {
            userService.deactivateUserByBarcode(barcode);
            return ResponseEntity.ok().build();
        } catch (NotFound notFound) {
            return ResponseEntity.notFound().build();
        }
    }
}
