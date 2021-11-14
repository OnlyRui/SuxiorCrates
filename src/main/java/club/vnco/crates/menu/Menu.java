package club.vnco.crates.menu;

import club.vnco.crates.menu.button.Button;
import club.vnco.crates.menu.type.FillType;
import club.vnco.crates.menu.type.MenuType;
import club.vnco.crates.utils.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter @Setter
public abstract class Menu {

    public static Map<UUID, Menu> currentlyOpenedMenus;

    private Inventory inventory;

    private String title;
    private int size;
    private MenuType menuType;

    private boolean fillEnabled, raw, cancelClick;
    private ItemStack fillItemStack;

    private FillType fillType;

    /**
     * Constructor to make a menu
     *
     * @param title - The title of the menu to be shown
     * @param size - The number of slots to be added to the menu (if Default)
     *               (Multiply by "9" as this is the minimum number of slots that
     *               a normal inventory can have).
     *
     * @param menuType - The type of menu you want to open
     */

    public Menu(String title, int size, MenuType menuType) {
        currentlyOpenedMenus = new HashMap<>();

        this.title = ChatUtil.translate(title);

        if (this.title.length() > 32) {
            this.title = title.substring(0, 32);
        }

        this.menuType = menuType;

        if (menuType == MenuType.DEFAULT) {
            this.size = size * 9;
        }

        this.cancelClick = true;

        this.fillEnabled = false;
        this.fillItemStack = null;

        this.fillType = null;

        if (this.inventory == null) {
            this.inventory = menuType.createMenu(this);
        }
    }

    /**
     * Another constructor to make a menu, but without being able to set the type of menu you want (Default Menu).
     */

    public Menu(String title, int size) {
        this(title, size, MenuType.DEFAULT);
    }

    /**
     * Open the menu to a player
     *
     * @param player - The player to whom the menu will be opened
     */

    public void openMenu(Player player, boolean raw) {
        this.raw = raw;

        Set<Button> buttons = this.getButtons(player);

        if (this.size == -1) {
            this.size = this.size(buttons);
            this.inventory = this.menuType.createMenu(this);
        }

        Menu previousMenu = Menu.currentlyOpenedMenus.get(player.getUniqueId());

        boolean update = false;

        InventoryView inventoryView = player.getOpenInventory();

        if (inventoryView != null) {
            Inventory topInventory = inventoryView.getTopInventory();

            if (previousMenu == null) {
                player.closeInventory();
            } else {

                if (topInventory.getSize() == this.size && topInventory.getTitle().equals(this.title)) {
                    this.inventory = topInventory;
                    update = true;
                } else {
                    player.closeInventory();
                }
            }
        }

        currentlyOpenedMenus.put(player.getUniqueId(), this);

        for (Button button : buttons) {
            this.inventory.setItem(button.getSlot(), button.getButtonItem());
        }

        if (this.isFillEnabled()) {
            if (this.fillType == null) {
                return;
            }

            this.fillType.applyFill(this);
        }

        this.onOpen(player);

        if (update) {
            player.updateInventory();
        } else {
            player.openInventory(this.inventory);
        }
    }

    /**
     * Opening the menu to a player will play what is in this method (If you are calling it) (Optional)
     *
     * @param player - The player to whom the contents of the method will be played back
     */

    public void onOpen(Player player) {
    }

    /**
     * When closing the menu, the content of this method will be reproduced.
     */

    public void onClose(InventoryCloseEvent event) {
    }

    private int size(Set<Button> buttons) {
        int highest = 0;

        for (Button button : buttons) {
            int buttonValue = button.getSlot();

            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int)(Math.ceil((highest + 1) / 9.0) * 9.0);
    }

    /**
     * This is the abstract method to be used to add buttons to the menu (if there are any)
     */

    public abstract Set<Button> getButtons(Player player);

}
