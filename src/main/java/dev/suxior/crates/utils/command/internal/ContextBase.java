package dev.suxior.crates.utils.command.internal;

import java.lang.reflect.Method;

import dev.suxior.crates.utils.command.annotation.Command;
import dev.suxior.crates.utils.command.context.Base;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContextBase implements Base {

    private Command command;
    private Method method;
    private Object instance;

    @Override
    public Command getCommand() {
        return (command);
    }

    @Override
    public Method getMethod() {
        return (method);
    }

    @Override
    public Object getInstance() {
        return (instance);
    }
}
