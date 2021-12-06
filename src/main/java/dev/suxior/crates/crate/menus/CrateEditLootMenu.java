package dev.suxior.crates.crate.menus;

import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.menus.buttons.CrateLootButton;
import dev.suxior.crates.utilities.BukkitUtil;
import dev.suxior.crates.utilities.menu.Menu;
import dev.suxior.crates.utilities.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class CrateEditLootMenu extends Menu {

    private final Crate crate;

    public CrateEditLootMenu(Crate crate) {
        super("&3Editing&7: &b" + crate.getId(), 6);
        this.crate = crate;

        this.setCancelClick(false);
    }

    @Override
    public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        ItemStack[] loot = this.crate.getLoot();

        for (int i = 0; i < loot.length; i++) {
            ItemStack item = loot[i];

            if (item == null) continue;

            buttons.add(new CrateLootButton(i, item));
        }

        return buttons;
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        if (!event.getView().getTopInventory().equals(this.getInventory())) {
            this.crate.setLoot(BukkitUtil.serializeInventory(this.getInventory().getContents()));
        }
    }
}
