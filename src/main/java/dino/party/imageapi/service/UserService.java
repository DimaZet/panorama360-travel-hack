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

    public boolean isUserActive(String barcode) throws NotFound {
        User user = userRepository.findByBarcode(barcode)
                .orElseThrow(NotFound::new);
        return user.isActive();
    }

    public User createUser(String barcode) {
        User user = new User(barcode, true);
        return userRepository.save(user);
    }
}
