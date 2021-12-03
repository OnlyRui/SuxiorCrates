package dev.suxior.crates.utilities.text;

import dev.suxior.crates.SuxiorCrates;
import dev.suxior.crates.controller.Controller;
import dev.suxior.crates.crate.Crate;
import dev.suxior.crates.stores.Store;
import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class BuildText {

    private final Store<Controller<SuxiorCrates>> controllerStore = SuxiorCrates.getInstance().getControllerStore();

    private final Map<String, String> map = new HashMap<>();
    private String staticText;
    private Matcher matcher;

    public List<String> of(CommandSender sender, Object object, List<String> list) {
        return list.stream().map(string -> of(sender, object, string)).collect(Collectors.toList());
    }

    public List<String> of(Object object, List<String> list) {
        return of(null, object, list);
    }

    public String of(Object object, String text) {
        return of(null, object, text);
    }

    public String of(CommandSender sender, Object object, String text){
        staticText = text;

        if (sender != null) {
            put("executor", sender.getName());
        }

        if (object instanceof Crate) {
            Crate crate = (Crate) object;

            put("crate", crate.getId());
            put("crateDisplayName", crate.getDisplayName());
        }

        for (Map.Entry<String, String> entry : map.entrySet()){
            if (text.contains(entry.getKey())){
                text = text.replace(entry.getKey(), entry.getValue());
            }
        }

        return ChatUtil.translate(text);
    }

    public void put(String key, String value){
        if (key.contains(":")){

            matcher = Pattern.compile("(\\<" + Pattern.quote(key.split(":")[0]) + ":)(.+?)(\\>)").matcher(staticText);

            if (matcher.find()){
                map.put("<" + key + matcher.group(2).trim() + ">", value);
            }

            return;
        }

        map.put("<" + key.trim() + ">", value);
    }
}