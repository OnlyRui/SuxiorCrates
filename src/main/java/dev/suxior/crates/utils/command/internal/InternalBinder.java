package dev.suxior.crates.utils.command.internal;

import java.util.Map;

import com.google.common.collect.Maps;

import dev.suxior.crates.utils.command.Key;
import dev.suxior.crates.utils.command.Module;
import dev.suxior.crates.utils.command.binding.AnnotatedBindingBuilder;
import dev.suxior.crates.utils.command.binding.Binder;
import dev.suxior.crates.utils.command.binding.Binding;

public class InternalBinder implements Binder {

    private final Map<Key<?>, Binding<?>> bindings;

    public InternalBinder(Module... modules) {
        this.bindings = Maps.newConcurrentMap();
        
        for (Module module : modules) {
            install(module);
        }
    }

    @Override
    public void install(Module module) {
        module.configure(this);
    }

    @Override
    public <T> AnnotatedBindingBuilder<T> bind(Key<T> key) {
        return (new InternalAnnotatedBindingBuilder<>(bindings, new InternalBinding<>(key)));
    }

    @Override
    public Map<Key<?>, Binding<?>> getBindings() {
        return (bindings);
    }

    @Override
    public <T> Binding<T> getBinding(Key<T> key) {
        Binding<?> binding = this.bindings.get(key);
        
        return (binding == null ? null : (Binding<T>) binding);
    }
}
