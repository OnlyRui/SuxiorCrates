package dev.suxior.crates.airdrop.inventory;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.config.ConfigController;
import dev.suxior.crates.config.ConfigFile;
import dev.suxior.crates.menu.Menu;
import dev.suxior.crates.menu.button.Button;
import dev.suxior.crates.utils.BukkitUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AirdropProvisionalInventory extends Menu {

    private final ConfigFile configFile = ((ConfigController) SuxiorCrates.getInstance().getController(ConfigController.class)).get("loot");

    public AirdropProvisionalInventory(Player player) {
        super("&3Airdrop Loot", 6);

        if (player.hasPermission("vcrates.airdrop.editloot")) {
            this.setCancelClick(false);
        }
    }

    @Override public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        Iterator<ItemStack> iterator = Arrays.asList(BukkitUtil.deserializeInventory((this.configFile.getString("airdrop-loot")))).iterator();

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
            this.configFile.set("airdrop-loot", this.getInventory().getContents());
        }
    }

}
