package dev.suxior.crates.utils.command.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParameterParseException extends RuntimeException {

	private static final long serialVersionUID = -1944040693398058633L;

	public ParameterParseException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ParameterParseException(Throwable cause) {
        super(cause);
    }
}
