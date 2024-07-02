package springrest.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    //Get all players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    //Get player by ID
    public Player getPlayerById(int id) {
        try {
            Optional<Player> player = playerRepository.findById(id);
            return player.orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("Player with id " + id + " not found!");
        }
    }

    //Add a player
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    //Update a player
    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    //Partially update a player

    //Delete a player
    public void deletePlayerById(int id) {
        playerRepository.deleteById(id);
    }
}
