package club.vnco.crates.crate.menus;

import club.vnco.crates.crate.Crate;
import club.vnco.crates.menu.Menu;
import club.vnco.crates.menu.button.Button;
import club.vnco.crates.utils.BukkitUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CrateEditLootMenu extends Menu {

    private final Crate crate;

    public CrateEditLootMenu(Crate crate) {
        super("&3Editing&7: &b" + crate.getId(), 6);
        this.crate = crate;

        this.setCancelClick(false);
    }

    @Override public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        ItemStack[] itemStacks = this.crate.getLoot();

        if (itemStacks.length > 0) {

            Iterator<ItemStack> iterator = Arrays.asList(itemStacks).iterator();
            int i = 0;

            while (iterator.hasNext() && i++ < this.getSize()) {
                buttons.add(new Button(i) {

                    @Override
                    public void onClick(Player player, ClickType clickType) {
                    }

                    @Override
                    public ItemStack getButtonItem() {
                        return iterator.next();
                    }

                });
            }
        }

        return buttons;
    }

    @Override public void onClose(InventoryCloseEvent event) {
        if (!event.getView().getTopInventory().equals(this.getInventory())) {
            this.crate.setLoot(BukkitUtil.serializeInventory(this.getInventory().getContents()));
        }
    }

}
