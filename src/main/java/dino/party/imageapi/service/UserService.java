package dino.party.imageapi.service;

import dino.party.imageapi.exception.NotFound;
import dino.party.imageapi.exception.UserAlreadyRegisteredException;
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

    public User createUser(String barcode) throws UserAlreadyRegisteredException {
        if (userRepository.findByBarcode(barcode).isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        User user = new User(barcode, true, false);
        return userRepository.save(user);
    }

    public void deactivateUserByBarcode(String barcode) throws NotFound {
        User user = userRepository.findByBarcode(barcode)
                .orElseThrow(NotFound::new);
        user.setActive(false);
        userRepository.save(user);
    }

    public User findFirstFromQue() throws NotFound {
        return userRepository.findAllByInQue(true).stream()
                .findFirst().orElseThrow(NotFound::new);
    }

    public void pushInQue(String barcode) throws NotFound {
        User user = userRepository.findByBarcode(barcode)
                .orElseThrow(NotFound::new);
        user.setInQue(true);
        userRepository.save(user);
    }

    public void removeFromQue(String barcode) throws NotFound {
        User user = userRepository.findByBarcode(barcode)
                .orElseThrow(NotFound::new);
        user.setInQue(false);
        userRepository.save(user);
    }
}
