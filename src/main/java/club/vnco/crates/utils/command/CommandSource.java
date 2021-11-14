package club.vnco.crates.utils.command;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public abstract class CommandSource<S> {

    private S handle;

    public void setHandle(Player handle) {
        this.handle = (S) handle;
    }

    public S getHandle() {
        return handle;
    }

    public abstract boolean hasPermission(String permission);

    public abstract void message(String message);

    public void message(String message, Object... args) {
        message(String.format(message, args));
    }
}
