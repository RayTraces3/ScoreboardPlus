package me.executed.scoreboardplus;

import co.aikar.commands.PaperCommandManager;
import fr.mrmicky.fastboard.adventure.FastBoard;
import lombok.Getter;
import me.executed.scoreboardplus.commands.ReloadCommand;
import me.executed.scoreboardplus.listeners.JoinLeaveListener;
import me.executed.scoreboardplus.utils.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ScoreboardPlus extends JavaPlugin {
    @Getter
    public static ScoreboardPlus instance;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(), this);

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");
        manager.registerCommand(new ReloadCommand());

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (FastBoard board : Scoreboard.getBoards().values()) {
                Scoreboard.updateBoard(board);
            }
        }, 0L, 10L);
        getLogger().info("Scoreboard+ has been enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Scoreboard+ has been disabled!");
    }

}