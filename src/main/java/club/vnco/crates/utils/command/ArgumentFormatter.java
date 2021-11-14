package club.vnco.crates.utils.command;

import java.util.List;

import club.vnco.crates.utils.command.context.Context;

public interface ArgumentFormatter {

    Object[] format(CommandSource<?> source, Context context, String... args);

    List<String> suggest(CommandSource<?> source, Context context, String lastWord, int pos);
}
