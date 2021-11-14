package club.vnco.crates.utils.command.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import club.vnco.crates.utils.command.exception.ParameterParseException;
import club.vnco.crates.utils.command.parameter.Parameter;
import club.vnco.crates.utils.command.parameter.ParameterAnnotation;
import club.vnco.crates.utils.command.parameter.ParameterParser;
import club.vnco.crates.utils.command.parameter.ParsedParameter;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import club.vnco.crates.utils.command.Key;
import club.vnco.crates.utils.command.binding.Binder;
import club.vnco.crates.utils.command.binding.Binding;
import club.vnco.crates.utils.command.defaults.enums.EnumBinding;

@AllArgsConstructor
public class InternalParameterParser implements ParameterParser {

    private Binder binder;

    @Override
    public List<Parameter<?>> parse(Method method) {
        List<Parameter<?>> parameters = Lists.newArrayList();

        for (java.lang.reflect.Parameter parameter : method.getParameters()) {
            Class<?> aClass = parameter.getType();
            boolean annotated = false;

            for (Annotation annotation : parameter.getAnnotations()) {
                if (annotation.annotationType().isAnnotationPresent(ParameterAnnotation.class)) {
                    annotated = true;
                }
            }

            Class<? extends Annotation> annotationType = annotated ? parameter.getDeclaredAnnotations()[0].annotationType() : null;
            Key<?> key = Key.get(aClass, annotationType);
            Binding<?> binding = binder.getBinding(key);

            if (binding == null) {
                if (aClass.isEnum()) {
                    binding = new EnumBinding(key);
                } else {
                    throw new ParameterParseException("Couldn't find a binding for %s%s", aClass, annotated ? " annotated with " + annotationType + "." : ".");
                }
            }

            parameters.add(new ParsedParameter<>(parameter, binding));
        }

        return (parameters);
    }

}
