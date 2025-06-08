package me.executed.scoreboardplus.listeners;

import fr.mrmicky.fastboard.adventure.FastBoard;
import me.executed.scoreboardplus.ScoreboardPlus;
import me.executed.scoreboardplus.utils.Scoreboard;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);

        String title = ScoreboardPlus.getInstance().getConfig().getString("scoreboard.title");
        board.updateTitle(LegacyComponentSerializer.legacyAmpersand().deserialize(title));

        Scoreboard.getBoards().put(player.getUniqueId(), board);


        Scoreboard.updateBoard(board);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FastBoard board = Scoreboard.getBoards().remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

}
