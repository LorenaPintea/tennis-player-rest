package springrest.tennisplayerrest;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
    public Player updatePlayer(int id, Player player) {
        //get player by ID
        try {
            Player updatePlayer = playerRepository.findById(id).orElseThrow();
            updatePlayer.setName(player.getName());
            updatePlayer.setBirthday(player.getBirthday());
            updatePlayer.setNationality(player.getNationality());
            updatePlayer.setTitles(player.getTitles());
            return playerRepository.save(updatePlayer);
        } catch (Exception e) {
            throw new RuntimeException("Player with id " + id + " couldn't be updated!");
        }
    }
    //Partially update a player
    public Player patch(int id, Map<String, Object> playerPatch) {
        try {
            Player player = playerRepository.findById(id).orElseThrow();

            playerPatch.forEach((key, value) -> {
               Field field = ReflectionUtils.findField(Player.class, key);
                if (field != null) {
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, player, value);
                }
            });

            return playerRepository.save(player);
        } catch (Exception e) {
            throw new RuntimeException("Player with id " + id + " couldn't be updated!");
        }
    }

    //Update titles
    //Transactional used in order to leave the DB in a consistent state.
    @Transactional
    public void updateTitles(int id, int titles) {
        playerRepository.updateTitles(id, titles);
    }

    //Delete a player
    public String deletePlayerById(int id) {
        try {
            playerRepository.deleteById(id);
            return "Player with id " + id + " deleted";
        } catch (Exception e) {
            return "Player with id " + id + " couldn't be deleted";
        }
    }
}
