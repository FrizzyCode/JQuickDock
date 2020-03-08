package com.frizz.dock.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

/**
 *
 *  @author Steven Frizell
 * @version 1.0.0a
 *
 */
public class SystemUtils {

    /**
     *
     */
    public enum OS { WINDOWS, LINUX, MAC, SOLARIS, UNDEFINED};

    /**
     * The users operating system.
     */
    private static OS os;

    /**
     *
     */
    private static String javaVersion;

    /**
     *
     */
    private static String javaHomeDir;

    /**
     *
     * @return
     */
    public static String getJavaVersion() {

        if (javaVersion == null) {
            javaVersion = System.getProperty("java.version").toLowerCase(Locale.ENGLISH);
        }

        return javaVersion;
    }

    /**
     *
     * @return
     */
    public static String getJavaHomeDirectory() {

        if (javaHomeDir == null) {
            javaHomeDir = System.getProperty("java.home");
        }

       return javaHomeDir;
    }

    /**
     * Retrieves the system specific icon for the specified file.
     * @return
     */
    public static final ImageIcon getSystemIcon(String sourcePath) throws FileNotFoundException {
        File file = new File(sourcePath);


        SafeIcon icon;
        ImageIcon imgIcon = null;

        icon = new SafeIcon(new JFileChooser().getIcon(file));

        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        icon.paintIcon(new JPanel(), image.getGraphics(), 0, 0);

        imgIcon = new ImageIcon(image);

        return imgIcon;
    }

    /**
     *
     * @param file
     * @param newDim
     * @return
     */
    public static final ImageIcon getSystemIcon(String sourcePath, Dimension newDim) throws FileNotFoundException {
        File file = new File(sourcePath);

        if (!file.exists())
            throw new FileNotFoundException("File does not exist");

        SafeIcon icon;
        ImageIcon imgIcon = null;

        icon = new SafeIcon(new JFileChooser().getIcon(file));

        int iconHeight = icon.getIconHeight();
        int iconWidth = icon.getIconWidth();

        if (iconHeight != newDim.height && iconWidth != newDim.width) {
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            icon.paintIcon(new JPanel(), image.getGraphics(), 0, 0);

            imgIcon = new ImageIcon(image.getScaledInstance(newDim.width, newDim.height, Image.SCALE_SMOOTH));
        }

        return imgIcon;
    }

    /**
     *
     * @return
     */
    public static final OS getOS() {

        if (os == null) {

         String sysos = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

         if (sysos.contains("win")) {
             os = OS.WINDOWS;
         } else if (sysos.contains("nix") || sysos.contains("nux") || sysos.contains("aix")) {
             os = OS.LINUX;
         } else if (sysos.contains("mac")) {
             os = OS.MAC;
         } else if (sysos.contains("sunos")) {
             os = OS.SOLARIS;
         } else {
             os = OS.UNDEFINED;
         }
        }

        return os;
    }

    /**
     *
     * @return
     */
    public static boolean isWindows() {
        if (getOS() == OS.WINDOWS) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public static boolean isLinux() {
        if (getOS() == OS.LINUX) {
            return true;
        }

        return false;
    }

    /**
     *
     */
    public static boolean isMac() {
        if (getOS() == OS.MAC) {
            return true;
        }

        return false;
    }

    /**
     *
     */
    public static boolean isSolaris() {
        if (getOS() == OS.SOLARIS) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public static boolean isUndefined() {
        if (getOS() == OS.UNDEFINED) {
            return true;
        }

        return false;
    }
}
