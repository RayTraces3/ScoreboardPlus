package me.executed.scoreboardplus.utils;

import fr.mrmicky.fastboard.adventure.FastBoard;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.executed.scoreboardplus.ScoreboardPlus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.util.*;

public class Scoreboard {

    @Getter
    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static void updateBoard(FastBoard board) {
        Player player = board.getPlayer();

        if (player == null || !player.isOnline()) {
            return;
        }

        List<Component> lines = new ArrayList<>();

        for(String line : ScoreboardPlus.getInstance().getConfig().getStringList("scoreboard.lines")) {
            lines.add(LegacyComponentSerializer.legacyAmpersand().deserialize(PlaceholderAPI.setPlaceholders(player, line)));
        }

        board.updateLines(lines);
    }
}
