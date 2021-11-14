package club.vnco.crates.crate.command;

import club.vnco.crates.Crates;
import club.vnco.crates.crate.Crate;
import club.vnco.crates.crate.CrateController;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrateProvider implements Provider<Crate> {

    private final CrateController controller = (CrateController) Crates.getInstance().getController(CrateController.class);

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
