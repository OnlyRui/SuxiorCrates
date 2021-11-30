package dev.suxior.crates.utils.command.parameter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;

import dev.suxior.crates.utils.command.binding.Binding;
import org.jetbrains.annotations.NotNull;

public class ParsedParameter<T> implements Parameter<T> {

    private final java.lang.reflect.Parameter parameter;
    private final Binding<T> binding;
    private final String name;

    public ParsedParameter(java.lang.reflect.Parameter parameter, Binding<T> binding) {
        this.parameter = parameter;
        this.binding = binding;

        if (parameter.isAnnotationPresent(Param.class)) {
            name = parameter.getAnnotation(Param.class).name();
        } else {
            name = parameter.getName();
        }

    }

    @Override
    public String getName() {
        return (name);
    }

    @Override
    public Binding<T> getBinding() {
        return (binding);
    }

    @Override
    public Iterator<Annotation> iterator() {
        return (Arrays.asList(parameter.getDeclaredAnnotations()).iterator());
    }

    @Override
    public <t extends Annotation> t getAnnotation(@NotNull Class<t> annotationClass) {
        return (parameter.getAnnotation(annotationClass));
    }

    @Override
    public Annotation[] getAnnotations() {
        return (parameter.getAnnotations());
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return (parameter.getDeclaredAnnotations());
    }
}
