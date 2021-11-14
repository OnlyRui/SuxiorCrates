package club.vnco.crates.utils.command.internal;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.google.common.base.Preconditions;

import club.vnco.crates.utils.command.Key;
import club.vnco.crates.utils.command.binding.AnnotatedBindingBuilder;
import club.vnco.crates.utils.command.binding.Binding;
import club.vnco.crates.utils.command.binding.BindingBuilder;

public class InternalAnnotatedBindingBuilder<T> extends AbstractBindingBuilder<T> implements AnnotatedBindingBuilder<T> {

    public InternalAnnotatedBindingBuilder(Map<Key<?>, Binding<?>> map, InternalBinding<T> binding) {
        super(map, binding);
    }

    @Override
    public BindingBuilder<T> annotatedWith(Class<? extends Annotation> annotationType) {
        Preconditions.checkState(binding.getKey().getAnnotationType() == null, "Annotation type already bound.");
        
        binding.getKey().setAnnotationType(annotationType);
        
        return (this);
    }
}
