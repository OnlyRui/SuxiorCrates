package dev.suxior.crates.utils.command.internal;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Nonnull;

import dev.suxior.crates.utils.command.parameter.Parameter;
import com.google.common.collect.Lists;

import dev.suxior.crates.utils.command.context.Context;
import dev.suxior.crates.utils.command.context.RootContext;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExecutableContext implements Context {

    private final Context parent;
    private List<String> aliases;
    private final String name;
    private String permission, description;
    private boolean async, help, forPlayerOnly;
    private Method method;
    private Object instance;
    private final List<Context> children;
    private final List<Parameter<?>> parameters;

    public ExecutableContext(Context parent, String name, Method method, Object instance) {
        this.parent = parent;
        this.name = name;
        this.aliases = Lists.newArrayList();
        this.method = method;
        this.instance = instance;
        this.children = Lists.newArrayList();
        this.parameters = Lists.newArrayList();
    }

    @Override
    public RootContext getRoot() {
        return (parent.getRoot());
    }

    @Override
    public boolean isRoot() {
        return (false);
    }

    @Override
    public Context getParent() {
        return (parent);
    }

    @Override
    public String getName() {
        return (name);
    }

    @Override
    public List<String> getAliases() {
        return (aliases);
    }

    @Override
    public String getPermission() {
        return (permission);
    }

    @Override
    public boolean hasPermission() {
        return (permission != null && !permission.isEmpty());
    }

    @Override
    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String getDescription() {
        return (description);
    }

    @Override
    public boolean hasDescription() {
        return (description != null && !description.isEmpty());
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isAsync() {
        return (async);
    }

    @Override
    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public boolean isHelp() {
        return (help);
    }

    @Override
    public void setHelp(boolean help) {
        this.help = help;
    }

    @Override
    public Method getMethod() {
        return (method);
    }

    @Override
    public Object getInstance() {
        return (instance);
    }

    @Override
    public List<Context> getChildren() {
        return (children);
    }

    @Override
    public void addChild(Context context) {
        this.children.add(context);
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return (parameters);
    }

    @Override
    public void merge(Context context) {
        this.aliases = context.getAliases();
        this.method = context.getMethod();
        this.instance = context.getInstance();
    }

    @Override
    public boolean matchesIgnoreCase(String check) {
        if (name.equalsIgnoreCase(check)) {
            return (true);
        }

        for (String alias : aliases) {
            if (alias.equalsIgnoreCase(check)) {
                return (true);
            }
        }

        return (false);
    }

    @Override
    public int compareTo(@Nonnull Context o) {
        return (String.CASE_INSENSITIVE_ORDER.compare(getName(), o.getName()));
    }
}
