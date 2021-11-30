package dev.suxior.crates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.suxior.crates.airdrop.AirdropController;
import dev.suxior.crates.commands.CommandController;
import dev.suxior.crates.config.ConfigController;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.menu.MenuRunnable;
import dev.suxior.crates.prepared.PreparedItemsController;
import dev.suxior.crates.reward.RewardController;
import dev.suxior.crates.stores.Store;
import dev.suxior.crates.stores.Stores;
import dev.suxior.crates.utils.TaskUtil;
import dev.suxior.crates.utils.item.ItemUtils;
import dev.suxior.crates.utils.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Getter @Setter
public class SuxiorCrates extends JavaPlugin {

    private Gson gson;
    private Store<Controller<SuxiorCrates>> controllerStore;

    @Override
    public void onEnable() {

        License license = new License(this, "MHYQ9-KBLQQ-LEW3X-YOAEH-KTW8B");

        if (license.verify()) {
            ChatUtil.print(

                    "&7&m-------------------------------------------------------",
                    "&c" + getDescription().getName() + " &7- &f" + getDescription().getVersion(),
                    "",
                    "&cAuthor&7: &f" + getDescription().getAuthors().get(0),
                    "&cSupport&7: &fhttps://discord.gg/ctfwMFjCVP",
                    ""
            );

            this.controllerStore = Stores.newClassStore();

            this.registerGson();
            this.registerControllers();

            ChatUtil.print(

                    "",
                    "&aLicense is valid.",
                    "&7&m-------------------------------------------------------"

            );
        }
        else {
            ChatUtil.print(
                    "&7&m-------------------------------------------------------",
                    "&c" + getDescription().getName() + " &7- &f" + getDescription().getVersion(),
                    "",
                    "&cAuthor&7: &f" + getDescription().getAuthors().get(0),
                    "&cSupport&7: &fhttps://discord.gg/ctfwMFjCVP",
                    "",
                    "&cLicense isn't valid.",
                    "&7&m-------------------------------------------------------"
            );
        }
    }

    @Override
    public void onDisable() {
        this.controllerStore.getAll().forEach(controller -> {

            controller.onDisable(this);
            this.controllerStore.remove(controller);

        });
    }

    private void registerGson() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    }

    private void registerControllers() {
        this.controllerStore = Stores.newClassStore();

        Arrays.asList(

                new ItemUtils(),
                new ConfigController(),
                new CrateController(),
                new RewardController(),
                new AirdropController(),
                new PreparedItemsController(),
                new CommandController()

        ).forEach(this::registerController);

        TaskUtil.runTaskTimer(MenuRunnable::new, 1L, 1L);
    }

    private void registerController(Controller<SuxiorCrates> controller) {
        this.controllerStore.add(controller);
        controller.onEnable(this);
    }

    public Object getController(Class<? extends Controller<SuxiorCrates>> clazz) {
        return this.controllerStore.get(clazz);
    }

    public static SuxiorCrates getInstance() {
        return getPlugin(SuxiorCrates.class);
    }
}
