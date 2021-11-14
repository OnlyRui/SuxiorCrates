package club.vnco.crates.prepared.command;

import club.vnco.crates.Crates;
import club.vnco.crates.prepared.PreparedItem;
import club.vnco.crates.prepared.PreparedItemsController;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PreparedItemProvider implements Provider<PreparedItem> {

    private final PreparedItemsController controller = (PreparedItemsController) Crates.getInstance().getController(PreparedItemsController.class);

    @Override public PreparedItem provide(CommandSource<?> source, String string, Parameter<PreparedItem> parameter) {
        Optional<PreparedItem> optionalPreparedItem = this.controller.findById(string);

        if (!optionalPreparedItem.isPresent()) {
            throw new ProviderException("&cPrepared Item &e%s &cnot found!", string);
        }

        return optionalPreparedItem.get();
    }

    @Override public List<String> suggest(CommandSource<?> source, Parameter<PreparedItem> parameter) {
        return this.controller.getItems().stream().map(PreparedItem::getId).collect(Collectors.toList());
    }
}
