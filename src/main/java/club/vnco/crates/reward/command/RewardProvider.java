package club.vnco.crates.reward.command;

import club.vnco.crates.Crates;
import club.vnco.crates.reward.Reward;
import club.vnco.crates.reward.RewardController;
import club.vnco.crates.utils.command.CommandSource;
import club.vnco.crates.utils.command.Provider;
import club.vnco.crates.utils.command.exception.ProviderException;
import club.vnco.crates.utils.command.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

public class RewardProvider implements Provider<Reward> {

    private final RewardController controller = (RewardController) Crates.getInstance().getController(RewardController.class);

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
