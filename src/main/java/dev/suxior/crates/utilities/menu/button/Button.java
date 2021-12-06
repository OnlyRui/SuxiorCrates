package dev.suxior.crates.utilities.menu.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public abstract class Button {

    private int slot;

    public Button(int slot) {
        this.slot = slot - 1;
    }

    /**
     * Get the ItemStack of the button
     */

    public abstract ItemStack getButtonItem();

    /**
     * The button click will play all actions within this abstract method.
     *
     * @param player - The player to whom the actions will be replayed
     * @param clickType - The type of click when touching the button
     */

    public void onClick(Player player, ClickType clickType) {

    }
}
