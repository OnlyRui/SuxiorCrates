package dev.suxior.crates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.suxior.crates.airdrop.AirdropController;
import dev.suxior.crates.airdrop.command.AirdropCommand;
import dev.suxior.crates.config.ConfigController;
import dev.suxior.crates.config.handlers.ConfigHandler;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.crate.command.CrateCommand;
import dev.suxior.crates.stores.Store;
import dev.suxior.crates.stores.Stores;
import dev.suxior.crates.utilities.TaskUtil;
import dev.suxior.crates.utilities.command.CommandManager;
import dev.suxior.crates.utilities.item.ItemUtils;
import dev.suxior.crates.utilities.menu.MenuRunnable;
import dev.suxior.crates.utilities.text.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Getter @Setter
public class SuxiorCrates extends JavaPlugin implements ConfigHandler {

    private Gson gson;
    private Store<Controller<SuxiorCrates>> controllerStore;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.controllerStore = Stores.newClassStore();
        this.registerControllers();

        License license = new License(this, getLicense());

        ChatUtil.print(
                "&7&m-------------------------------------------------------",
                "&b&l" + getDescription().getName() + " &7- &f" + getDescription().getVersion(),
                "",
                "&bAuthor&7: &f" + getDescription().getAuthors().get(0),
                "&bSupport&7: &fhttps://discord.gg/ctfwMFjCVP",
                ""
        );

        if (license.verify()) {
            this.commandManager = new CommandManager(this);

            this.registerGson();
            this.registerCommands();

            ChatUtil.print(
                    "&aLicense is valid.",
                    "&7&m-------------------------------------------------------"
            );
        }
        else {
            ChatUtil.print(
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
                new AirdropController()

        ).forEach(this::registerController);

        TaskUtil.runTaskTimer(MenuRunnable::new, 1L, 1L);
    }

    private void registerCommands() {
        new CrateCommand();
        new AirdropCommand();
    }

    private void registerController(Controller<SuxiorCrates> controller) {
        this.controllerStore.add(controller);
        controller.onEnable(this);
    }

    public Object getController(Class<? extends Controller<SuxiorCrates>> clazz) {
        return this.controllerStore.get(clazz);
    }

    private String getLicense() {
        return (String) this.pathBase();
    }

    private Object pathBase() {
        return this.get("license", "license");
    }

    public static SuxiorCrates getInstance() {
        return getPlugin(SuxiorCrates.class);
    }
}
