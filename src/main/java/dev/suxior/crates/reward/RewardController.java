package dev.suxior.crates.reward;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.cache.Storager;
import dev.suxior.crates.config.ConfigController;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.utils.item.ItemCreator;
import dev.suxior.crates.utils.item.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RewardController implements Storager<Reward>, Controller<SuxiorCrates> {

    private Map<String, Reward> rewardMap;

    @Override public void onEnable(SuxiorCrates crates) {
        this.rewardMap = new HashMap<>();

        this.registerRewards();

        Bukkit.getPluginManager().registerEvents(new RewardListener(this), crates);
    }

    @Override public void onReload(SuxiorCrates crates) {
        this.registerRewards();
    }

    private void registerRewards() {
        this.clear();

        ConfigurationSection section = ((ConfigController) this.getController(ConfigController.class)).get("rewards")
                .getConfigurationSection("rewards");

        if (section == null) {
            return;
        }

        for (String key : section.getKeys(false)) {
            ItemStack itemStack = ItemUtils.parseItem(section.getString(key + ".material-id"));

            if (itemStack != null) {
                this.add(key, new Reward(

                        key,
                        section.getStringList(key + ".commands"),
                        new ItemCreator(itemStack)
                                .setDisplayName(section.getString(key + ".display-name"))
                                .setGlow(section.getBoolean(key + ".glow"))
                                .setLore(section.getStringList(key + ".description")).toItemStack()

                        ));
            }
        }
    }

    @Override public Map<String, Reward> get() {
        return this.rewardMap;
    }

}
