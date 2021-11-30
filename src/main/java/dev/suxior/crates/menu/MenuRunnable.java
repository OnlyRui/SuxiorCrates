package dev.suxior.crates.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MenuRunnable implements Runnable {

    @Override public void run() {
        Menu.currentlyOpenedMenus.forEach(((uuid, menu) -> {

            if (menu == null) {
                return;
            }

            Player player = Bukkit.getPlayer(uuid);

            if (player != null) {
                menu.openMenu(player, true);
            }

        }));
    }

}
