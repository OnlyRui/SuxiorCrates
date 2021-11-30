package dev.suxior.crates.prepared.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.prepared.PreparedItem;
import dev.suxior.crates.prepared.PreparedItemsController;
import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;
import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PreparedItemProvider implements Provider<PreparedItem> {

    private final PreparedItemsController controller = (PreparedItemsController) SuxiorCrates.getInstance().getController(PreparedItemsController.class);

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
