package executed.scoreboard;

import executed.scoreboard.utils.Scoreboard;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor {

    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        scoreboard = new Scoreboard(this);
        getCommand("spreload").setExecutor(this);
        getLogger().info("Scoreboard+ has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Scoreboard+ has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spreload")) {
            // Handle the reload command here
            reloadConfig();
            scoreboard = new Scoreboard(this); // Reinitialize scoreboard with new config
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lSCOREBOARD+ &8| &fConfig.yml Reloaded"));
            return true;
        }
        return false;
    }
}