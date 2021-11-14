package club.vnco.crates;

import club.vnco.crates.utils.Crypter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ryzeon
 * Project: rLicenses
 * Date: 10/02/2021 @ 20:05
 */

public class License {

    protected final String license;
    protected final String plugin;
    protected final String apiKey = "jbqkMtqBQAyrVlXEioUOrNpKRbRo";
    protected final String server = "http://162.255.85.52:6969";
    private ErrorType errorType;

    private String buyer;
    private String generateDate;

    private boolean valid = false;

    private boolean debug = false;

    public License(String license, Plugin plugin) {
        this.license = license;
        this.plugin = plugin.getDescription().getName();
    }

    public void request() {
        try {
            URL url = new URL(server + "/api/check/request/licenses?keyAPI=" + apiKey + "&license=" + license + "&plugin=" + plugin + "&ip=" + getIP());
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }

            String response = builder.toString();

            response = Crypter.decrypt(response);

            if (debug) {
                System.out.println(response);
            }

            if (response.equalsIgnoreCase("API_KEY_NOT_VALID")) {
                errorType = ErrorType.API_KEY_NOT_VALID;
            } else if (response.equalsIgnoreCase("INVALID_LICENSE")) {
                errorType = ErrorType.INVALID_LICENSE;
            } else if (response.equalsIgnoreCase("INVALID_PLUGIN_NAME")) {
                errorType = ErrorType.INVALID_PLUGIN_NAME;
            } else if (response.equalsIgnoreCase("INVALID_IP")) {
                errorType = ErrorType.INVALID_IP;
            } else if (response.equalsIgnoreCase("EXPIRED")) {
                errorType = ErrorType.EXPIRED;
            } else if (response.startsWith("VALID")) {
                errorType = ErrorType.VALID;
                valid = true;
                String[] split = response.split(";");
                this.buyer = split[1];
                this.generateDate = split[3];
            } else {
                errorType = ErrorType.PAGE_ERROR;
            }
        } catch (IOException e) {
            if (debug) {
                e.printStackTrace();
            }
            valid = false;
            errorType = ErrorType.PAGE_ERROR;
        }
    }

    public String getLicense() {
        return this.license;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getBuyer() {
        return this.buyer;
    }

    public String getGenerateDate() {
        return this.generateDate;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public enum ErrorType {
        PAGE_ERROR,
        API_KEY_NOT_VALID,
        INVALID_LICENSE,
        INVALID_PLUGIN_NAME,
        INVALID_IP,
        EXPIRED,
        VALID
    }

    private String getIP() {
        String ipAddress = "";
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            String response = builder.toString();
            ipAddress = response;
        } catch (MalformedURLException e) {
            ipAddress = "NONE";
        } catch (IOException e) {
            ipAddress = "NONE";
        }
        return ipAddress + ":" + Bukkit.getServer().getPort();
    }
}

