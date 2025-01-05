package me.t3sl4.util.os.device;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

public class DeviceUtil {

    /**
     * Retrieves device information as a JSON string.
     *
     * @return A JSON string containing OS details, hardware information, and network addresses.
     */
    public static String getDeviceInfoAsJson() {
        try {
            String osName = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");
            String osArch = System.getProperty("os.arch");
            int availableProcessors = Runtime.getRuntime().availableProcessors();

            long maxMemory = Runtime.getRuntime().maxMemory();
            long totalMemory = Runtime.getRuntime().totalMemory();

            String ipAddress = getIpAddress();
            String externalIpAddress = getExternalIpAddress();
            String hwid = getHardwareId();

            JSONObject deviceInfoJson = new JSONObject();
            deviceInfoJson.put("osName", osName);
            deviceInfoJson.put("osVersion", osVersion);
            deviceInfoJson.put("osArch", osArch);
            deviceInfoJson.put("availableProcessors", availableProcessors);
            deviceInfoJson.put("maxMemory", maxMemory);
            deviceInfoJson.put("totalMemory", totalMemory);
            deviceInfoJson.put("ipAddress", ipAddress);
            deviceInfoJson.put("externalIpAddress", externalIpAddress);
            deviceInfoJson.put("hwid", hwid);

            return deviceInfoJson.toString(4);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * Retrieves the internal IP address of the device.
     *
     * @return The internal IP address, or "Unknown" if it cannot be determined.
     */
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        if (inetAddress instanceof Inet4Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    /**
     * Retrieves the external IP address of the device.
     *
     * @return The external IP address, or "Unknown" if it cannot be retrieved.
     */
    public static String getExternalIpAddress() {
        String ipAddress = "Unknown";
        try {
            URL url = new URL("http://api.ipify.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            ipAddress = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

    /**
     * Retrieves the hardware ID (MAC address) of the device.
     *
     * @return The MAC address as a string, or "Unknown" if it cannot be retrieved.
     */
    public static String getHardwareId() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (networkInterface != null && !networkInterface.isLoopback() && networkInterface.getHardwareAddress() != null) {
                    byte[] macBytes = networkInterface.getHardwareAddress();
                    StringBuilder macAddress = new StringBuilder();
                    for (byte b : macBytes) {
                        macAddress.append(String.format("%02X", b));
                    }
                    return macAddress.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}
