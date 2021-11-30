package dev.suxior.crates.utils.command.context;

import java.lang.reflect.Method;

import dev.suxior.crates.utils.command.annotation.Command;

public interface Base {
	
    Command getCommand();

    Method getMethod();

    Object getInstance();
}
