package club.vnco.crates.utils.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import club.vnco.crates.utils.command.parameter.ParameterAnnotation;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParameterAnnotation
public @interface Sender {
	
}
