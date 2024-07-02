package springrest.tennisplayerrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Modifying
    @Query("UPDATE Player player SET player.titles = :titles WHERE player.id = :id")
    void updateTitles(@Param("id") int id, @Param("titles") int titles);
}
