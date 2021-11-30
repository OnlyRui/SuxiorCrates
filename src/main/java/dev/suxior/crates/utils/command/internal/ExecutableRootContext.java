package dev.suxior.crates.utils.command.internal;

import java.lang.reflect.Method;

import dev.suxior.crates.utils.command.context.RootContext;

public class ExecutableRootContext extends ExecutableContext implements RootContext {

    public ExecutableRootContext(String name, Method method, Object instance) {
        super(null, name, method, instance);
    }

    @Override
    public RootContext getRoot() {
        return (this);
    }

    @Override
    public boolean isRoot() {
        return (true);
    }
}
