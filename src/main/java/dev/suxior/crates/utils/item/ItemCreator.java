package dev.suxior.crates.utils.item;

import dev.suxior.crates.utils.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class ItemCreator {

    private static ItemStack itemStack;
    private static ItemMeta itemMeta;
    private static Enchantment FAKE_GLOW = new FakeGlow(70);

    public ItemCreator(Material material){
        this(material, 1, (short) 0);
    }

    public ItemCreator(Material material, int amount){
        this(new ItemStack(material, amount, (short) 0));
    }

    public ItemCreator(Material material, short data){
        this(new ItemStack(material, 1, data));
    }

    public ItemCreator(Material material, String amount, short data){
        this(new ItemStack(material, Integer.parseInt(amount), data));
    }

    public ItemCreator(Material material, int amount, short data){
        this(new ItemStack(material, amount, data));
    }

    public ItemCreator(ItemStack itemStack){
        ItemCreator.itemStack = itemStack;
        itemMeta = itemStack.getItemMeta();
    }

    public static ItemStack makeItem(Material material){
        return makeItem(material, 1, (short) 0, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount){
        return makeItem(material, amount, (short) 0, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data){
        return makeItem(material, amount, data, null, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display){
        return makeItem(material, amount, data, display, (String) null);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display, String lore){
        List<String> realLore = new ArrayList<>();
        Collections.addAll(realLore, lore);
        return makeItem(material, amount, data, display, realLore);
    }

    public static ItemStack makeItem(Material material, int amount, short data, String display, List<String> lore) {
        itemStack = new ItemStack(material, amount, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (display != null) {
            itemMeta.setDisplayName(display);
        }
        if (lore != null) {
            itemMeta.setLore(lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void setDisplayName(ItemStack itemStack, String displayName){
        itemMeta.setDisplayName(ChatUtil.translate(displayName));
        itemStack.setItemMeta(itemMeta);
    }

    public ItemCreator setMaterial(Material material){
        ItemCreator.itemStack = new ItemStack(material);
        return this;
    }

    public ItemCreator setAmount(int amount){
        ItemCreator.itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator setData(int data){
        ItemCreator.itemStack.setData(new MaterialData((byte) data));
        return this;
    }

    public ItemCreator setDisplayName(String displayName){
        itemMeta.setDisplayName(ChatUtil.translate(displayName));
        ItemCreator.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setLore(List<String> lore){
        ItemMeta itemMeta = ItemCreator.itemStack.getItemMeta();
        itemMeta.setLore(ChatUtil.translate(lore));
        ItemCreator.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setLore(String... lore){
        List<String> realLore = new ArrayList<>();
        Collections.addAll(realLore, lore);
        setLore(ItemCreator.itemStack, realLore);
        return this;
    }

    public ItemCreator setColor(org.bukkit.Color color, boolean b){
        if (b) {
            Material material = itemStack.getType();

            if (material.equals(Material.LEATHER_BOOTS) || material.equals(Material.LEATHER_CHESTPLATE) || material.equals(Material.LEATHER_LEGGINGS) || material.equals(Material.LEATHER_HELMET)) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
                leatherArmorMeta.setColor(color);
                itemStack.setItemMeta(leatherArmorMeta);
            }
        }

        return this;
    }

    public static ItemStack setLore(ItemStack itemStack, String... lore) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, lore);
        return setLore(itemStack, list);
    }

    public static ItemStack setLore(ItemStack itemStack, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(ChatUtil.translate(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemCreator setDurability(ItemStack itemStack, short durability){
        itemStack.setDurability(durability);
        return this;
    }

    public ItemCreator setGlow(boolean glow){
        if (glow){
            addFakeGlow(itemStack);
        }
        return this;
    }

    public static void setUnbreakable(ItemStack itemStack, boolean unbreakable) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.spigot().setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);
    }

    public static void setSkullOwner(ItemStack itemStack, String skullOwner) {
        if (itemStack.getType() == Material.SKULL_ITEM) {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(skullOwner);
            itemStack.setItemMeta(skullMeta);
        }
    }

    public static void addUnbreaking(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null) {
                if (itemStack.getType() != Material.AIR) {
                    Material type = itemStack.getType();
                    if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD || type == Material.GOLD_SWORD || type == Material.IRON_SWORD || type == Material.DIAMOND_SWORD || type == Material.BOW || type == Material.DIAMOND_HELMET || type == Material.DIAMOND_CHESTPLATE || type == Material.DIAMOND_LEGGINGS || type == Material.DIAMOND_BOOTS) {
                        setUnbreakable(itemStack, true);
                    }
                }
            }
        }
    }

    public static void removeUnbreaking(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null) {
                if (itemStack.getType() != Material.AIR) {
                    Material type = itemStack.getType();
                    if (type == Material.WOOD_SWORD || type == Material.STONE_SWORD || type == Material.GOLD_SWORD || type == Material.IRON_SWORD || type == Material.DIAMOND_SWORD || type == Material.BOW) {
                        setUnbreakable(itemStack, false);
                    }
                }
            }
        }
    }

    public static void addEnchant(ItemStack itemStack, Enchantment enchantment, int level){
        if (itemStack != null){
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(enchantment, level, true);
            itemStack.setItemMeta(itemMeta);
        }
    }

    public static ItemStack getEnchantedBook(Enchantment enchantment, int level){
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta)itemStack.getItemMeta();
        itemMeta.addStoredEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void setColor(ItemStack itemStack, org.bukkit.Color color){
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
    }

    public static void setColor(ItemStack[] itemStacks, org.bukkit.Color color){
        for (ItemStack itemStack : itemStacks) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(color);
            itemStack.setItemMeta(leatherArmorMeta);
        }
    }

    public static void setRgbColor(ItemStack[] itemStacks, int red, int green, int blue){
        for (ItemStack itemStack : itemStacks) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(red, green, blue));
            itemStack.setItemMeta(leatherArmorMeta);
        }
    }

    public static void setRgbColor(ItemStack itemStack, int red, int green, int blue){
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(red, green, blue));
        itemStack.setItemMeta(leatherArmorMeta);
    }

    public static void addFakeGlow(ItemStack[] itemStacks){
        for (ItemStack itemStack : itemStacks) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(FAKE_GLOW, 2, false);
            itemStack.setItemMeta(itemMeta);
        }
    }

    public static void addFakeGlow(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(FAKE_GLOW, 2, false);
        itemStack.setItemMeta(itemMeta);
    }

    public ItemStack toItemStack(){
        return itemStack;
    }

    private static class FakeGlow extends EnchantmentWrapper {

        public FakeGlow(int id) {
            super(id);
        }

        public int getStartLevel() {
            return 1;
        }

        public int getMaxLevel() {
            return 10;
        }

        @Override public EnchantmentTarget getItemTarget() {
            return super.getItemTarget();
        }

        @Override public boolean canEnchantItem(ItemStack itemStack) {
            return super.canEnchantItem(itemStack);
        }

        @Override public Enchantment getEnchantment() {
            return super.getEnchantment();
        }

        public boolean conflictsWith(Enchantment enchantment) {
            return false;
        }

    }

}
