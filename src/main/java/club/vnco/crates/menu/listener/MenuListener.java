package club.vnco.crates.menu.listener;

import club.vnco.crates.menu.Menu;
import club.vnco.crates.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MenuListener implements Listener {

    @EventHandler public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        Menu menu = Menu.currentlyOpenedMenus.get(player.getUniqueId());

        if (menu != null) {
            if (menu.isCancelClick()) {
                event.setCancelled(true);
            }

            for (Button button : menu.getButtons(player)) {
                if (event.getSlot() == button.getSlot()) {
                    button.onClick(player, event.getClick());
                }
            }

        }
    }

    @EventHandler public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        Menu menu = Menu.currentlyOpenedMenus.get(player.getUniqueId());

        if (menu != null) {
            menu.onClose(event);

            Menu.currentlyOpenedMenus.remove(player.getUniqueId());
        }
    }

}
