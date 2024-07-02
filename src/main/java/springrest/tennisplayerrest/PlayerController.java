package springrest.tennisplayerrest;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player REST API";
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        player.setId(0);
        return playerService.addPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @PatchMapping("/players/{id}")
    public Player patchPlayer(@PathVariable int id, @RequestBody Map<String, Object> patchPlayer) {
        return playerService.patch(id, patchPlayer);
    }

    @PatchMapping("/players/{id}/titles")
    public void updateTitles(@PathVariable int id, @RequestBody int titles) {
        playerService.updateTitles(id, titles);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id) {
        return playerService.deletePlayerById(id);
    }
}
