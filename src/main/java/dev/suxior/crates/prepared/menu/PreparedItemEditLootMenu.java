package dev.suxior.crates.prepared.menu;

import dev.suxior.crates.menu.Menu;
import dev.suxior.crates.menu.button.Button;
import dev.suxior.crates.menu.type.MenuType;
import dev.suxior.crates.prepared.PreparedItem;
import dev.suxior.crates.utils.BukkitUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PreparedItemEditLootMenu extends Menu {

    private final PreparedItem preparedItem;

    public PreparedItemEditLootMenu(PreparedItem preparedItem) {
        super("&3Editing P-Item&7: &b" + preparedItem.getId(), 1, MenuType.HOPPER);
        this.preparedItem = preparedItem;

        this.setCancelClick(false);
    }

    @Override public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        Iterator<ItemStack> iterator = Arrays.asList(this.preparedItem.getLoot()).iterator();
        int i = 0;

        while (iterator.hasNext() && i++ < this.getSize()) {
            buttons.add(new Button(i) {

                @Override public void onClick(Player player, ClickType clickType) {}

                @Override public ItemStack getButtonItem() {
                    return iterator.next();
                }

            });
        }

        return buttons;
    }

    @Override public void onClose(InventoryCloseEvent event) {
        if (!event.getView().getTopInventory().equals(this.getInventory())) {
            this.preparedItem.setLoot(BukkitUtil.serializeInventory(this.getInventory().getContents()));
        }
    }

}
