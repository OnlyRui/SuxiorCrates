package dev.suxior.crates.reward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter @AllArgsConstructor
public class Reward {

    private String name;
    private List<String> commands;
    private ItemStack itemStack;

}
