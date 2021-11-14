package club.vnco.crates.airdrop;

import club.vnco.crates.Crates;
import club.vnco.crates.airdrop.inventory.AirdropProvisionalInventory;
import club.vnco.crates.cache.Cache;
import club.vnco.crates.controller.Controller;
import club.vnco.crates.utils.RandomCollections;
import club.vnco.crates.utils.item.ItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class AirdropController implements Cache<UUID, Airdrop>, Controller<Crates> {

    private Map<UUID, Airdrop> airdropMap;

    @Override public void onEnable(Crates crates) {
        this.airdropMap = new HashMap<>();
    }

    public ItemStack[] getRandomLoot(Player player) {
        Inventory inventory = new AirdropProvisionalInventory(player).getInventory();

        if (inventory == null) {
            return new ItemStack[0];
        }

        List<ItemStack> itemStacks = new ArrayList<>();
        RandomCollections<ItemStack> randomCollections = new RandomCollections<>();

        int freeSlots = 0;

        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                freeSlots++;
            }
        }

        for (int i = 0; i < 54; i++) {
            ItemStack itemStack = inventory.getItem(i);

            if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.getType() != Material.STAINED_GLASS_PANE) {
                randomCollections.add(new Random().nextInt(freeSlots), itemStack);
            }
        }

        int i = 0;

        while (i++ < 9) {
            itemStacks.add(randomCollections.next());
        }

        return itemStacks.toArray(new ItemStack[0]);
    }

    public Airdrop findAt(Location location) {
        for (Airdrop airdrop : this.values()) {
            if (airdrop.getLocation().equals(location)) {
                return airdrop;
            }
        }
        return null;
    }

    public ItemStack getSummonerItem() {
        return new ItemCreator(Material.TORCH)
                .setDisplayName("&b&lAirdrop &7(Summoner)")
                .setLore("&fPlace for spawn a airdrop!")
                .toItemStack();
    }

    @Override public Map<UUID, Airdrop> get() {
        return this.airdropMap;
    }

}
