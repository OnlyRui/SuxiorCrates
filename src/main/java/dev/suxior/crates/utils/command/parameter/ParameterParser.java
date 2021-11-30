package dev.suxior.crates.utils.command.parameter;

import java.lang.reflect.Method;
import java.util.List;

public interface ParameterParser {

    List<Parameter<?>> parse(Method method);

}
