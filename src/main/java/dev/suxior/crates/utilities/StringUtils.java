package dev.suxior.crates.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.World;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class StringUtils {

    public StringBuilder buildString(String[] array, int start) {
        StringBuilder builder = new StringBuilder();

        for(int i = start; i != array.length; ++i) {
            builder.append(array[i]).append(" ");
        }

        return builder;
    }

    public boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException exception) {
            return false;
        }
    }

    public String capitalize(String string) {
        return String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1);
    }

    public String getWorldName(World world){
        switch (world.getEnvironment()){

            case NORMAL: {
                return "World";
            }

            case THE_END: {
                return "End";
            }

            case NETHER: {
                return "Nether";
            }

            default: {
                return world.getName();
            }
        }
    }

    public long parse(String input) {
        if (input == null || input.isEmpty()) {
            return -1L;
        }

        long result = 0L;

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < input.length(); ++i) {

            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                String str;

                if (Character.isLetter(c) && !(str = number.toString()).isEmpty()) {
                    result += convert(Integer.parseInt(str), c);
                    number = new StringBuilder();
                }
            }
        }
        return result;
    }

    private long convert(int value, char unit) {
        switch (unit) {
            case 'y': {
                return value * TimeUnit.DAYS.toMillis(365L);
            }
            case 'M': {
                return value * TimeUnit.DAYS.toMillis(30L);
            }
            case 'd': {
                return value * TimeUnit.DAYS.toMillis(1L);
            }
            case 'h': {
                return value * TimeUnit.HOURS.toMillis(1L);
            }
            case 'm': {
                return value * TimeUnit.MINUTES.toMillis(1L);
            }
            case 's': {
                return value * TimeUnit.SECONDS.toMillis(1L);
            }
            default: {
                return -1L;
            }
        }
    }
}
