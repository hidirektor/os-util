package me.t3sl4.util.os.desktop;

import mslinks.ShellLink;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DesktopUtil {

    /**
     * Configures system properties based on the operating system and application requirements.
     *
     * <p>This method sets specific properties for JavaFX and logging behaviors, ensuring compatibility
     * and optimized performance across different platforms.</p>
     *
     * Properties configured:
     * - Disables HiDPI scaling for JavaFX.
     * - Sets rendering and animation options for Windows.
     * - Adjusts PDF rendering and UI settings for macOS.
     * - Configures logging level to 'WARNING'.
     *
     * Platform-specific settings:
     * <ul>
     *   <li>Windows: Enables software rendering and high-performance animations.</li>
     *   <li>macOS: Configures PDF rendering verbosity and hides UI elements.</li>
     * </ul>
     */
    public static void configureSystemProperties() {
        System.setProperty("prism.allowhidpi", "false");

        if(System.getProperty("os.name").contains("Windows")) {
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.verbose", "true");
            System.setProperty("javafx.animation.fullspeed", "true");
        } else {
            System.setProperty("CG_PDF_VERBOSE", "1");
            System.setProperty("apple.awt.UIElement", "true");
        }
        System.setProperty("java.util.logging.level", "WARNING");
    }

    /**
     * Creates a desktop shortcut.
     * @param fileName The name of the shortcut.
     * @param targetPath The target file or program path.
     * @param iconPath The path to the shortcut icon.
     * @param workingDirectory The working directory for the shortcut.
     * @throws IOException If an error occurs during creation.
     */
    public static void createDesktopShortcut(String fileName, String targetPath, String iconPath, String workingDirectory) throws IOException {
        File home = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = home.getAbsolutePath();
        File desktopDir = new File(desktopPath);

        if (!desktopDir.exists() && !desktopDir.mkdirs()) {
            throw new IOException("Desktop directory could not be created: " + desktopPath);
        }

        String shortcutPath = desktopPath + "\\" + fileName + ".lnk";

        ShellLink sl = new ShellLink()
                .setTarget(targetPath)
                .setWorkingDir(workingDirectory)
                .setIconLocation(iconPath);
        sl.getHeader().setIconIndex(0);

        sl.saveTo(shortcutPath);
        System.out.println("Shortcut created: " + shortcutPath);
    }

    /**
     * Adds a program to Windows startup folder.
     * @param fileName The name of the shortcut.
     * @param targetPath The target file or program path.
     * @param iconPath The path to the shortcut icon.
     * @param workingDirectory The working directory for the shortcut.
     * @throws IOException If an error occurs during creation.
     */
    public static void addToStartup(String fileName, String targetPath, String iconPath, String workingDirectory) throws IOException {
        String startupPath = System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
        File startupDir = new File(startupPath);

        if (!startupDir.exists() && !startupDir.mkdirs()) {
            throw new IOException("Startup directory could not be created: " + startupPath);
        }

        String shortcutPath = startupPath + "\\" + fileName + ".lnk";

        ShellLink sl = new ShellLink()
                .setTarget(targetPath)
                .setWorkingDir(workingDirectory)
                .setIconLocation(iconPath);
        sl.getHeader().setIconIndex(0);

        sl.saveTo(shortcutPath);
        System.out.println("Added to startup: " + shortcutPath);
    }

    /**
     * Opens a specified file or folder using the default system application.
     * @param path The path to the file or folder to open.
     * @throws IOException If an error occurs during opening.
     */
    public static void startExternalApplicationAsync(String path) {
        new Thread(() -> {
            try {
                File file = new File(path);
                if (!file.exists()) {
                    throw new IOException("File or directory does not exist: " + path);
                }

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    throw new UnsupportedOperationException("Desktop is not supported on this platform.");
                }
            } catch (IOException | UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
