package club.vnco.crates.prepared;

import club.vnco.crates.Crates;
import club.vnco.crates.controller.Controller;
import club.vnco.crates.json.JsonSerializer;
import club.vnco.crates.stores.Store;
import club.vnco.crates.stores.Stores;
import club.vnco.crates.utils.FileUtils;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Getter
public class PreparedItemsController implements JsonSerializer<PreparedItem>, Controller<Crates> {

    private Store<PreparedItem> itemsStore;

    @Override public void onEnable(Crates crates) {
        this.itemsStore = Stores.newNamedStore();

        this.loadPreparedItems();
    }

    @Override public void onDisable(Crates crates) {
        this.savePreparedItems();
    }

    @Override public void onReload(Crates crates) {
        this.loadPreparedItems();
    }

    private void loadPreparedItems() {
        File[] files = this.getParentFile().listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            String fileContent = FileUtils.read(file);

            if (fileContent != null) {
                this.itemsStore.add(this.fromJson(fileContent, PreparedItem.class));
            }
        }
    }

    private void savePreparedItems() {
        for (PreparedItem item : this.getItems()) {

            if (item == null) {
                continue;
            }

            FileUtils.write(FileUtils.getOrCreateFile(new File(this.getParentFile(), item.getId() + ".json")),
                    this.toJson(item, PreparedItem.class));
        }
    }

    public Optional<PreparedItem> findById(String id) {
        return this.getItems().stream().filter(preparedItem -> preparedItem.getId().equalsIgnoreCase(id)).findFirst();
    }

    public List<PreparedItem> getItems() {
        return this.itemsStore.getAll();
    }

    @Override public File getParentFile() {
        return new File(this.getInstance().getDataFolder(), "prepared-items");
    }

}
