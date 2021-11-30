package dev.suxior.crates.crate.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.crate.CrateController;
import dev.suxior.crates.utils.command.CommandSource;
import dev.suxior.crates.utils.command.Provider;
import dev.suxior.crates.utils.command.exception.ProviderException;
import dev.suxior.crates.utils.command.parameter.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrateProvider implements Provider<Crate> {

    private final CrateController controller = (CrateController) SuxiorCrates.getInstance().getController(CrateController.class);

    @Override public Crate provide(CommandSource<?> source, String string, Parameter<Crate> parameter) {
        Optional<Crate> optionalCrate = this.controller.findCrate(string);

        if (!optionalCrate.isPresent()) {
            throw new ProviderException("&cCrate &e%s &cnot found!", string);
        }

        return optionalCrate.get();
    }

    @Override public List<String> suggest(CommandSource<?> source, Parameter<Crate> parameter) {
        return this.controller.getCrates().stream().map(Crate::getId).collect(Collectors.toList());
    }

}
