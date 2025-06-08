package executed.scoreboard.utils;

import fr.mrmicky.fastboard.adventure.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Scoreboard implements Listener {
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final Plugin plugin;

    public Scoreboard(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);


        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }
        }, 0L, 10L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);


        String title = plugin.getConfig().getString("scoreboard.title");
        board.updateTitle(LegacyComponentSerializer.legacyAmpersand().deserialize(title));


        boards.put(player.getUniqueId(), board);


        updateBoard(board);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FastBoard board = boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {
        Player player = board.getPlayer();


        if (player == null || !player.isOnline()) {
            return;
        }


        List<Component> lines = new ArrayList<>();

        for(String line : plugin.getConfig().getStringList("scoreboard.lines")) {
            lines.add(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, line)));
        }

        board.updateLines(lines);
    }
    }

