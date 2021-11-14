package club.vnco.crates.config;

import club.vnco.crates.Crates;
import club.vnco.crates.cache.Cache;
import club.vnco.crates.controller.Controller;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ConfigController implements Cache<String, ConfigFile>, Controller<Crates> {

    private Map<String, ConfigFile> fileMap;

    @Override public void onEnable(Crates crates) {
        this.fileMap = new HashMap<>();

        Arrays.asList(

                "config",
                "airdrop",
                "rewards",
                "loot",
                "language"

        ).forEach(name -> this.add(name, new ConfigFile(name + ".yml")));
    }

    @Override public void onReload(Crates crates) {
        this.forEach((s, configFile) -> configFile = new ConfigFile(s + ".yml"));
    }

    @Override public Map<String, ConfigFile> get() {
        return this.fileMap;
    }

}
