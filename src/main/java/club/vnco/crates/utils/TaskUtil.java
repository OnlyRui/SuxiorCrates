package club.vnco.crates.utils;

import club.vnco.crates.Crates;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ThreadFactory;

@UtilityClass
public class TaskUtil {

    private final Crates crates = Crates.getInstance();
    private final BukkitScheduler scheduler = Bukkit.getScheduler();

    public static ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }

    public static ThreadFactory newThreadFactory(String name, Thread.UncaughtExceptionHandler handler) {
        return new ThreadFactoryBuilder().setNameFormat(name).setUncaughtExceptionHandler(handler).build();
    }

    public BukkitTask runTaskTimer(Callback callback, long delay, long tick){
        return scheduler.runTaskTimer(crates, callback::call, delay, tick);
    }

    public BukkitTask runTaskLater(Callback callback, long tick){
        return scheduler.runTaskLater(crates, callback::call, tick);
    }

    public static BukkitTask asyncLater(Callback callback, long delay) {
        return scheduler.runTaskLaterAsynchronously(crates, callback::call, delay);
    }

    public BukkitTask runTaskTimerAsync(Callback callback, long delay, long tick){
        return scheduler.runTaskTimerAsynchronously(crates, callback::call, delay, tick);
    }

    public BukkitTask runTaskAsync(Callback callback){
        return scheduler.runTaskAsynchronously(crates, callback::call);
    }

    public BukkitTask runTask(Callback callback){
        return scheduler.runTask(crates, callback::call);
    }

    public void cancel(int id){
        scheduler.cancelTask(id);
    }

    public interface Callback {

        void call();

    }

}
