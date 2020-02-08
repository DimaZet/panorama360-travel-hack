package dino.party.imageapi.service;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.model.User;
import dino.party.imageapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByBarcode(String barcode) throws NotFound {
        return userRepository.findByBarcode(barcode)
                .orElseThrow(NotFound::new);
    }
}
