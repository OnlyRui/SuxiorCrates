package club.vnco.crates.reward;

import club.vnco.crates.Crates;
import club.vnco.crates.cache.Storager;
import club.vnco.crates.config.ConfigController;
import club.vnco.crates.controller.Controller;
import club.vnco.crates.utils.item.ItemCreator;
import club.vnco.crates.utils.item.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RewardController implements Storager<Reward>, Controller<Crates> {

    private Map<String, Reward> rewardMap;

    @Override public void onEnable(Crates crates) {
        this.rewardMap = new HashMap<>();

        this.registerRewards();

        Bukkit.getPluginManager().registerEvents(new RewardListener(this), crates);
    }

    @Override public void onReload(Crates crates) {
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
