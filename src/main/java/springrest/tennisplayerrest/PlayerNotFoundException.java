package springrest.tennisplayerrest;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String s) {
        super(s);
    }
}
