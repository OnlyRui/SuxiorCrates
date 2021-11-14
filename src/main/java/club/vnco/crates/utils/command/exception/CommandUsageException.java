package club.vnco.crates.utils.command.exception;

import club.vnco.crates.utils.command.context.Context;

public class CommandUsageException extends CommandException {

	private static final long serialVersionUID = 836526998249349813L;

	public CommandUsageException(String[] args, Context context) {
        super(context);
    }

    public CommandUsageException(String[] args, Context context, String message, Object... arguments) {
        super(context, message, arguments);
    }
}
