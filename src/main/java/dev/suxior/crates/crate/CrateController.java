package dev.suxior.crates.crate;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.json.JsonSerializer;
import dev.suxior.crates.stores.Store;
import dev.suxior.crates.stores.Stores;
import dev.suxior.crates.utils.BukkitUtil;
import dev.suxior.crates.utils.FileUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
public class CrateController implements JsonSerializer<Crate>, Controller<SuxiorCrates> {

    private Store<Crate> crateStore;
    public static String META = "§1§2§3";

    @Override
    public void onEnable(SuxiorCrates crates) {
        this.crateStore = Stores.newNamedStore();

        this.loadCrates();

        Bukkit.getPluginManager().registerEvents(new CrateListener(this), crates);
    }

    @Override
    public void onDisable(SuxiorCrates crates) {
        this.saveCrates();
    }

    @Override
    public void onReload(SuxiorCrates crates) {
        this.loadCrates();
    }

    private void loadCrates() {
        File[] files = this.getParentFile().listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            String fileContent = FileUtils.read(file);

            if (fileContent != null) {
                this.crateStore.add(this.fromJson(fileContent, Crate.class));
            }
        }
    }

    private void saveCrates() {
        for (Crate crate : this.getCrates()) {

            if (crate == null) {
                continue;
            }

            FileUtils.write(FileUtils.getOrCreateFile(new File(this.getParentFile(), crate.getId() + ".json")),
                    this.toJson(crate, Crate.class));
        }
    }

    public Optional<Crate> findCrate(String name) {
        return this.filterCrates(crate -> crate.getId().equalsIgnoreCase(name));
    }
    
    public Optional<Crate> findCrate(Location location) {
        return this.filterCrates(crate -> crate.getLocations().contains(BukkitUtil.serializeLocation(location)));
    }

    private Optional<Crate> filterCrates(Predicate<Crate> predicate) {
        return this.getCrates().stream().filter(predicate).findFirst();
    }
    
    public List<Crate> getCrates() {
        return this.crateStore.getAll();
    }

    @Override public File getParentFile() {
        return new File(this.getInstance().getDataFolder(), "crates");
    }
}
