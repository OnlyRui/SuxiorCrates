package dev.suxior.crates.airdrop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.UUID;

@Getter @AllArgsConstructor
public class Airdrop {

    private final UUID uuid;
    private final Location location;

    public void selfDestruct() {
        this.location.getBlock().setType(Material.AIR);
    }

    @SuppressWarnings("deprecation")
    public void fall() {
        this.location.getWorld().spawnFallingBlock(this.location.clone().add(0.5, 30, 0.5), Material.DISPENSER, new MaterialData(Material.DISPENSER).getData());
    }

}
