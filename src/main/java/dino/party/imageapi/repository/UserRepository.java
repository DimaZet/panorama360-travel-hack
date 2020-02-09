package dino.party.imageapi.repository;

import java.util.List;
import java.util.Optional;

import dino.party.imageapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByBarcode(String barcode);

    List<User> findAllByInQue(boolean inQue);
}
