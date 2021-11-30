package dev.suxior.crates.config;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.cache.Cache;
import dev.suxior.crates.controller.Controller;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ConfigController implements Cache<String, ConfigFile>, Controller<SuxiorCrates> {

    private Map<String, ConfigFile> fileMap;

    @Override public void onEnable(SuxiorCrates crates) {
        this.fileMap = new HashMap<>();

        Arrays.asList(

                "config",
                "airdrop",
                "rewards",
                "loot",
                "language"

        ).forEach(name -> this.add(name, new ConfigFile(name + ".yml")));
    }

    @Override public void onReload(SuxiorCrates crates) {
        this.forEach((s, configFile) -> configFile = new ConfigFile(s + ".yml"));
    }

    @Override public Map<String, ConfigFile> get() {
        return this.fileMap;
    }

}
