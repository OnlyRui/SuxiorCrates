package dev.suxior.crates.reward.command;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.reward.Reward;
import dev.suxior.crates.reward.RewardController;
import dev.suxior.crates.utilities.command.CommandSource;
import dev.suxior.crates.utilities.command.Provider;
import dev.suxior.crates.utilities.command.exception.ProviderException;
import dev.suxior.crates.utilities.command.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

public class RewardProvider implements Provider<Reward> {

    private final RewardController controller = (RewardController) SuxiorCrates.getInstance().getController(RewardController.class);

    @Override public Reward provide(CommandSource<?> source, String string, Parameter<Reward> parameter) {
        Reward reward = this.controller.getByName(string);

        if (reward == null) {
            throw new ProviderException("&cReward &e%s &cnot found!", string);
        }

        return reward;
    }

    @Override public List<String> suggest(CommandSource<?> source, Parameter<Reward> parameter) {
        return new ArrayList<>(this.controller.keySet());
    }

}
