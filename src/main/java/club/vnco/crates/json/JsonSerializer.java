package club.vnco.crates.json;

import club.vnco.crates.Crates;
import com.google.gson.Gson;

import java.io.File;

/**
 * vCrates
 * By: @OldVnco
 */
public interface JsonSerializer<T> {

    default String toJson(T t, Class<T> tClass) {
        return this.getGson().toJson(t, tClass);
    }

    default T fromJson(String content, Class<T> t) {
        return this.getGson().fromJson(content, t);
    }

    /**
     * Get plugin {@link Gson}
     * @return - gson
     */
    default Gson getGson() {
        return Crates.getInstance().getGson();
    }

    /**
     * Get the parent {@link File}
     * @return - parent file
     */
    File getParentFile();

}
