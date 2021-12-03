package dev.suxior.crates.utilities.text;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {

    public String translate(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> stringList) {
        return stringList.stream().map(ChatUtil::translate).collect(Collectors.toList());
    }

    public void print(String... message){
        Arrays.asList(message).forEach(log -> Bukkit.getConsoleSender().sendMessage(translate(log)));
    }

    public void toPlayer(Player player, List<String> stringList){
        stringList.forEach(message -> toPlayer(player, message));
    }

    public void toPlayer(Player player, String... longMessage){
        Arrays.asList(longMessage).forEach(message -> toPlayer(player, message));
    }

    public void toPlayer(Player player, String message){
        player.sendMessage(translate(message));
    }

    public void toSender(CommandSender sender, List<String> stringList){
        stringList.forEach(message -> toSender(sender, message));
    }

    public void toSender(CommandSender sender, String... longMessage){
        Arrays.asList(longMessage).forEach(message -> toSender(sender, message));
    }

    public void toSender(CommandSender sender, String message){
        sender.sendMessage(translate(message));
    }

    public String strip(String message) {
        return ChatColor.stripColor(message);
    }

}
