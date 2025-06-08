package executed.scoreboard.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import executed.scoreboard.ScoreboardPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandAlias("sp|scoreboardplus")
public class ReloadCommand extends BaseCommand {

    @HelpCommand
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @CommandPermission("scoreboardplus.command.reload")
    @Subcommand("reload")
    public void reload(CommandSender sender) {
        ScoreboardPlus.getInstance().reloadConfig();

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lSCOREBOARD+ &8| &fConfig.yml Reloaded"));
    }

}