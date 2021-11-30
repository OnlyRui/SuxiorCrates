package dev.suxior.crates.utils.command.binding;

import java.lang.annotation.Annotation;

public interface AnnotatedBindingBuilder<T> extends BindingBuilder<T> {

    BindingBuilder<T> annotatedWith(Class<? extends Annotation> annotationType);

}
