package club.vnco.crates.utils.command.context;

import java.lang.reflect.Method;
import java.util.List;

import club.vnco.crates.utils.command.parameter.Parameter;

public interface Context extends Comparable<Context> {

    RootContext getRoot();

    boolean isRoot();

    Context getParent();

    String getName();

    List<String> getAliases();

    String getPermission();

    boolean hasPermission();

    void setPermission(final String permission);

    String getDescription();

    boolean hasDescription();

    void setDescription(final String description);

    boolean isAsync();

    void setAsync(final boolean async);

    boolean isForPlayerOnly();

    void setForPlayerOnly(final boolean forPlayerOnly);

    boolean isHelp();

    void setHelp(final boolean help);

    Method getMethod();

    Object getInstance();

    List<Context> getChildren();

    void addChild(final Context context);

    List<Parameter<?>> getParameters();

    void merge(final Context context);

    boolean matchesIgnoreCase(final String check);
}
