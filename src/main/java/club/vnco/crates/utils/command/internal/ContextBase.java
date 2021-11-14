package club.vnco.crates.utils.command.internal;

import java.lang.reflect.Method;

import club.vnco.crates.utils.command.annotation.Command;
import lombok.AllArgsConstructor;
import club.vnco.crates.utils.command.context.Base;

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
