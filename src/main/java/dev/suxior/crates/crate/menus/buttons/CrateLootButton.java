package dev.suxior.crates.crate.menus.buttons;

import dev.suxior.crates.utilities.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Risas
 * Project: SuxiorCrates
 * Date: 05-12-2021 - 23:06
 * Twitter: @RisasDev
 * GitHub: https://github.com/RisasDev
 */

public class CrateLootButton extends Button {

    private final ItemStack itemStack;

    public CrateLootButton(int slot, ItemStack itemStack) {
        super(slot);
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack getButtonItem() {
        return itemStack;
    }

    @Override
    public void onClick(Player player, ClickType clickType) {

    }
}
