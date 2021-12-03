package dev.suxior.crates.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

@UtilityClass
public class BukkitUtil {

    /**
     * Transforms a {@link Location} to a {@link String}
     *
     * @param location the {@link Location} to serialize
     * @return the location {@link String}
     */
    public String serializeLocation(Location location) {
        if (location == null){
            return "";
        }

        StringBuilder serializedData = new StringBuilder();

        serializedData.append(location.getWorld().getName()).append(", ");
        serializedData.append(location.getX()).append(", ");
        serializedData.append(location.getY()).append(", ");
        serializedData.append(location.getZ()).append(", ");
        serializedData.append(location.getYaw()).append(", ");
        serializedData.append(location.getPitch());

        return serializedData.toString();
    }

    /**
     * Transforms a {@link String} to a {@link Location}
     *
     * @param data the {@link String} to deserialize
     * @return a {@link Location} instance
     */
    public Location deserializeLocation(String data) {
        if (data.equals("")){
            return null;
        }

        String[] splittedData = data.split(", ");

        if (splittedData.length < 6) {
            return null;
        }

        try {
            return new Location(

                    Bukkit.getWorld(splittedData[0]),
                    Double.parseDouble(splittedData[1]),
                    Double.parseDouble(splittedData[2]),
                    Double.parseDouble(splittedData[3]),
                    Float.parseFloat(splittedData[4]),
                    Float.parseFloat(splittedData[5])

            );
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public ItemStack deserializeItemStack(String data){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new BigInteger(data, 32).toByteArray());
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        ItemStack itemStack = null;
        try {
            Class<?> nbtTagCompoundClass = getNMSClass("NBTTagCompound");
            Class<?> nmsItemStackClass = getNMSClass("ItemStack");
            Object nbtTagCompound = getNMSClass("NBTCompressedStreamTools").getMethod("a", DataInputStream.class).invoke(null, dataInputStream);
            //Object nbtTagCompound = getNMSClass("NBTCompressedStreamTools").getMethod("a", DataInputStream.class).invoke(null, inputStream);
            Object craftItemStack = nmsItemStackClass.getMethod("createStack", nbtTagCompoundClass).invoke(null, nbtTagCompound);
            itemStack = (ItemStack) getOBClass("inventory.CraftItemStack").getMethod("asBukkitCopy", nmsItemStackClass).invoke(null, craftItemStack);
        } catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        return itemStack;
    }

    public String serializeItemStack(ItemStack item) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(outputStream);

        try {
            Class<?> nbtTagCompoundClass = getNMSClass("NBTTagCompound");
            Constructor<?> nbtTagCompoundConstructor = nbtTagCompoundClass.getConstructor();
            Object nbtTagCompound = nbtTagCompoundConstructor.newInstance();
            Object nmsItemStack = getOBClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
            getNMSClass("ItemStack").getMethod("save", nbtTagCompoundClass).invoke(nmsItemStack, nbtTagCompound);
            getNMSClass("NBTCompressedStreamTools").getMethod("a", nbtTagCompoundClass, DataOutput.class).invoke(null, nbtTagCompound, dataOutput);

        } catch (SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //return BaseEncoding.base64().encode(outputStream.toByteArray());
        return new BigInteger(1, outputStream.toByteArray()).toString(32);
    }

    private Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." +  Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private Class<?> getOBClass(String name) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public String serializeInventory(ItemStack[] items) {
        if (items == null) {
            return "";
        }

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.length);

            for (ItemStack item : items) {
                dataOutput.writeObject(item);
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public ItemStack[] deserializeInventory(String data) {
        if (data.equals("")) {
            return new ItemStack[0];
        }

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ItemStack[0];
    }

}
