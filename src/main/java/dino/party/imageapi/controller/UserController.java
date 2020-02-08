package dino.party.imageapi.controller;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.exception.UserAlreadyRegisteredException;
import dino.party.imageapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Check is user still active")
    @GetMapping("/{barcode}/isActive")
    public ResponseEntity isUserActive(
            @ApiParam(name = "barcode", required = true) @PathVariable String barcode
    ) {
        try {
            return ResponseEntity.ok(
                    userService.isUserActive(barcode));
        } catch (NotFound notFound) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Add active user")
    @PostMapping
    public ResponseEntity addUser(
            @ApiParam(name = "barcode", required = true) @RequestParam(name = "barcode") String barcode
    ) {
        try {
            return ResponseEntity.ok(
                    userService.createUser(barcode));
        } catch (UserAlreadyRegisteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Deactivate user")
    @DeleteMapping
    public ResponseEntity deactivateUser(
            @ApiParam(name = "barcode", required = true) @RequestParam(name = "barcode") String barcode) {
        try {
            userService.deactivateUserByBarcode(barcode);
            return ResponseEntity.ok().build();
        } catch (NotFound notFound) {
            return ResponseEntity.notFound().build();
        }
    }
}
