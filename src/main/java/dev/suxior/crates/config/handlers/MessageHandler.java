package dev.suxior.crates.config.handlers;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.config.ConfigController;
import dev.suxior.crates.config.ConfigFile;
import dev.suxior.crates.utilities.text.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public interface MessageHandler {

    default void sendMessageAll(String path, String... toReplace) {
        Bukkit.getOnlinePlayers().forEach(player -> this.sendMessage(player, path, toReplace));
        this.sendMessage(Bukkit.getConsoleSender(), path, toReplace);
    }

    default void sendMessage(CommandSender sender, String path, String... toReplace) {
        ConfigFile configFile = ((ConfigController) SuxiorCrates.getInstance().getController(ConfigController.class)).get("language");
        String message = configFile.getString("prefix") + (configFile.getString(path));

        for (String replace : toReplace) {
            ChatUtil.toSender(sender, (message).replace("<" + replace.split("/")[0] + ">", replace.split("/")[1]));
        }
    }

}
