package club.vnco.crates;

import club.vnco.crates.airdrop.AirdropController;
import club.vnco.crates.commands.CommandController;
import club.vnco.crates.config.ConfigController;
import club.vnco.crates.controller.Controller;
import club.vnco.crates.crate.CrateController;
import club.vnco.crates.menu.MenuRunnable;
import club.vnco.crates.prepared.PreparedItemsController;
import club.vnco.crates.reward.RewardController;
import club.vnco.crates.stores.Store;
import club.vnco.crates.stores.Stores;
import club.vnco.crates.utils.TaskUtil;
import club.vnco.crates.utils.item.ItemUtils;
import club.vnco.crates.utils.text.ChatUtil;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Getter @Setter
public class Crates extends JavaPlugin {

    private Gson gson;
    private Store<Controller<Crates>> controllerStore;

    @Override public void onEnable() {
        if (!this.getDescription().getName().equals("vCrates") || !this.getDescription().getAuthors().contains("OldVnco")){
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        /*License license = new License(Config.LICENSE_KEY, this);
        license.request();

        if (!license.isValid()) {
            ChatUtil.print(

                    Strings.repeat("-", 55),
                    "&cvHub &7- &fVnco Development",
                    "",
                    "&cInvalid Key!",
                    "&cSupport&7: &fdiscord.vnco.club",
                    Strings.repeat("-", 55)

            );

            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }*/

        ChatUtil.print(

                Strings.repeat("-", 55),
                "&bvCrates &7- &fVnco Development",
                "",
                "&aValid Key!"

        );

        this.registerGson();
        this.registerControllers();

        ChatUtil.print(

                "",
                "&bSupport&7: &fdiscord.vnco.club",
                Strings.repeat("-", 55)

        );
    }

    @Override public void onDisable() {
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

    private void registerController(Controller<Crates> controller) {
        this.controllerStore.add(controller);
        controller.onEnable(this);
    }

    public Object getController(Class<? extends Controller<Crates>> clazz) {
        return this.controllerStore.get(clazz);
    }

    public static Crates getInstance(){
        return getPlugin(Crates.class);
    }

}
