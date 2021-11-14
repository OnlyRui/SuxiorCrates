package club.vnco.crates.utils.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String[] aliases();

    String permission() default "";

    String description() default "";

    boolean async() default false;

    boolean forPlayerOnly() default false;

    boolean help() default false;
}
