package dino.party.imageapi.exception;

public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
