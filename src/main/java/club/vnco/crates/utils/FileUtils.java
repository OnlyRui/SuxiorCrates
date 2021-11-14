package club.vnco.crates.utils;

import lombok.experimental.UtilityClass;

import java.io.*;

/**
 * vHCF
 * By: @OldVnco
 */
@SuppressWarnings("all")
@UtilityClass
public class FileUtils {

    public void createFile(File file) {
        createFile(file.getParentFile(), file.getName());
    }

    public void createFile(File dir, String fileName) {
        File file = new File(dir, fileName);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public File getOrCreateFile(File file) {
        if (!file.exists()) {
            createFile(file);
        }

        return file;
    }

    public void write(File file, String toWrite) {
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(toWrite);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public String read(File file) {
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            int value = reader.read();

            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }

            if (builder.length() == 0) {
                return null;
            }

            return builder.toString();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
