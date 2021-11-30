package dev.suxior.crates.config;

import dev.suxior.crates.SuxiorCrates;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFile extends YamlConfiguration {

    private final SuxiorCrates plugin = SuxiorCrates.getInstance();
    private final String fileName;
    private File file;

    public ConfigFile(String fileName) {
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.create();
        this.reload();
    }

    public void create(){
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                if (plugin.getResource(this.fileName) != null) {
                    plugin.saveResource(this.fileName, false);
                } else {
                    file.createNewFile();
                }
            }
            super.load(file);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void reload(){
        this.file = new File(this.plugin.getDataFolder(), fileName);

        if (!this.file.exists()) {
            plugin.saveResource(fileName, false);
        }

        YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            super.save(file);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
