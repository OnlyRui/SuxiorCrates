package dev.suxior.crates.utils.command.internal;

import dev.suxior.crates.utils.command.parameter.Parameter;
import dev.suxior.crates.utils.command.UsageGenerator;
import dev.suxior.crates.utils.command.context.Context;

public class InternalUsageGenerator implements UsageGenerator {

    @Override
    public String generate(Context context) {
        StringBuilder builder = new StringBuilder();

        for (Context child : context.getChildren()) {
            builder.append(child.getName()).append(" ").append(generate(child));
        }

        for (int i = 0; i < context.getParameters().size(); i++) {
            Parameter<?> parameter = context.getParameters().get(i);
            if (parameter.isSender()) {
                continue;
            }

            boolean o = parameter.isOptional();
            builder.append(o ? "[" : "<")
                    .append(parameter.getName())
                    .append(parameter.isBooleanType() ? "?" : "")
                    .append(parameter.isText() ? "..." : "")
                    .append(o ? "]" : ">");

            if (i != context.getParameters().size() - 1) {
                builder.append(" ");
            }
        }

        return (builder.toString());
    }
}
