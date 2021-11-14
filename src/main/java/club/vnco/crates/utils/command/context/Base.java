package club.vnco.crates.utils.command.context;

import java.lang.reflect.Method;

import club.vnco.crates.utils.command.annotation.Command;

public interface Base {
	
    Command getCommand();

    Method getMethod();

    Object getInstance();
}
