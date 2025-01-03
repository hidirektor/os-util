package me.t3sl4.util.os.desktop;

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
}
