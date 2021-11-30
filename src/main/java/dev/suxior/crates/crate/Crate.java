package dev.suxior.crates.crate;

import dev.suxior.crates.config.handlers.ConfigHandler;
import dev.suxior.crates.stores.Storeable;
import dev.suxior.crates.utils.BukkitUtil;
import dev.suxior.crates.utils.item.ItemCreator;
import dev.suxior.crates.utils.item.ItemUtils;
import dev.suxior.crates.utils.text.BuildText;
import dev.suxior.crates.utils.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public class Crate implements Storeable<String>, ConfigHandler {

    private String id, displayName, blockItem, keyItem, loot;
    private Set<String> locations;

    public Crate(String id) {
        this.id = id;
        this.locations = new HashSet<>();

        this.loot = BukkitUtil.serializeInventory(new ItemStack[]{new ItemCreator(Material.APPLE).setDisplayName("&buwu").toItemStack()});

        this.reset();
    }

    public void giveBlock(Player player) {
        ItemStack storagedBlock = this.getBlockItem();

        if (!storagedBlock.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = storagedBlock.getItemMeta();

        if (!itemMeta.hasDisplayName()) {
            return;
        }

        player.getInventory().addItem(new ItemCreator(storagedBlock)
                .setDisplayName(itemMeta.getDisplayName() + CrateController.META)
                .toItemStack());

        ChatUtil.toPlayer(player, "&aSuccessfully gived &e" + this.displayName + " &7Block!");
    }

    public void giveKey(Player player, int amount) {
        ItemStack storagedItem = this.getKeyItem();

        if (!storagedItem.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = storagedItem.getItemMeta();

        if (!itemMeta.hasDisplayName()) {
            return;
        }
        String displayName = itemMeta.getDisplayName();

        ItemStack itemStack = new ItemCreator(storagedItem)
                .setDisplayName(displayName + CrateController.META)
                .setAmount(amount)
                .toItemStack();

        PlayerInventory inventory = player.getInventory();

        if (inventory.firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
            ChatUtil.toPlayer(player, "&aYou key has been dropped in the floor!");
        } else {
            inventory.addItem(itemStack);
            ChatUtil.toPlayer(player, "&aSuccessfully added to your inventory " + displayName + "&7(x" + amount + ")");
        }

    }

    public boolean isValidKey(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta.hasDisplayName()) {
            String displayName = ChatUtil.strip(itemMeta.getDisplayName());

            return displayName.contains(this.id) && displayName.contains(CrateController.META);
        }

        return false;
    }

    public void reset() {
        this.displayName = (String) this.pathBase("display-name");

        this.blockItem = this.buildItem("block");
        this.keyItem = this.buildItem("key");
    }

    @SuppressWarnings("unchecked")
    private String buildItem(String section) {
        String key = section + ".";

        ItemStack itemStack = ItemUtils.parseItem(this.getStringAt(key + "material-id"));

        if (itemStack == null) {
            return null;
        }

        return BukkitUtil.serializeItemStack(

                new ItemCreator(itemStack)
                        .setDisplayName(BuildText.of(this, this.getStringAt(key + "display-name")))
                        .setGlow((boolean) this.pathBase(key + "glow"))
                        .setLore(BuildText.of(this, (List<String>) this.pathBase(key + "description")))
                        .toItemStack()

        );
    }

    private String getStringAt(String path) {
        return (String) this.pathBase(path);
    }

    private Object pathBase(String path) {
        return this.get("config", "crate-template." + path);
    }

    public ItemStack getBlockItem() {
        return BukkitUtil.deserializeItemStack(this.blockItem);
    }

    public ItemStack getKeyItem() {
        return BukkitUtil.deserializeItemStack(this.keyItem);
    }

    public ItemStack[] getLoot() {
        return BukkitUtil.deserializeInventory(this.loot);
    }

}
