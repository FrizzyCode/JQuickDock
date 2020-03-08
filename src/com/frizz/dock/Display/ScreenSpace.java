package com.frizz.dock.Display;


import java.awt.*;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.stage.Screen;

/**
 * A class of static utility methods that helps calculate screen space bounds and
 * where the dock should be placed.
 * @author Steven
 * @version 1.0.0a
 */
public  class ScreenSpace {

    /**
     *
     */
    private static ScreenSpace screenSpace;

    /**
     *
     */
    private final Logger logger = Logger.getLogger(ScreenSpace.class.getPackage().getName());

    /**
     *
     */
    private final Preferences prefs = Preferences.userNodeForPackage( ScreenSpace.class );

    /**
     * Local Graphics Environment.
     */
    private GraphicsEnvironment LOCAL_ENVIRONMENT;

    /**
     * Local display devices.
     */
    private GraphicsDevice[] LOCAL_DEVICES;

    /**
     * The default display device.
     */
    private GraphicsDevice PRIMARY_DEVICE;

    /**
     * The usable screen space for the primary (default) screen device.
     */
    private Rectangle PRIMARY_DEVICE_BOUNDS;

    /**
     *
     */
    public int DOCK_OFFSETX = prefs.getInt( "dock-offset-x", 60 );

    /**
     *
     */
    public int DOCK_OFFSETY = prefs.getInt( "dock-offset-y", 20 );

    /**
     *
     */
    private ScreenSpace() {
        cacheVars();
    }

    /**
     * Caches the internal variables.
     */
    private void cacheVars() {
        getLocalGraphicsEnvironment();
        getLocalDevices();
        getPrimaryDevice();
        getSysPrimaryUseableBounds();
    }

    /**
     * Updates internal variables on getInstance() to ensure information
     * on the GraphicsDevice is correct.
     */
    private void updateVars() {
        LOCAL_DEVICES = null;
        LOCAL_ENVIRONMENT = null;
        PRIMARY_DEVICE_BOUNDS = null;
        PRIMARY_DEVICE = null;
        cacheVars();
    }

    /**
     *
     * @return
     */
    public static ScreenSpace getInstance() {
        if (screenSpace == null) {
            screenSpace = new ScreenSpace();
        } else {
            screenSpace.updateVars();
        }

        return screenSpace;
    }

    /**
     * Retrieves the system GraphicsEnvironment.
     */
    public GraphicsEnvironment getLocalGraphicsEnvironment() {
        if ( LOCAL_ENVIRONMENT == null) {
            LOCAL_ENVIRONMENT = GraphicsEnvironment.getLocalGraphicsEnvironment();
        }

        return LOCAL_ENVIRONMENT;
    }

    /**
     * Retrieves the screen devices of the user's setup.
     * @return
     */
    public GraphicsDevice[] getLocalDevices() {
        if (LOCAL_DEVICES == null) {
            LOCAL_DEVICES = LOCAL_ENVIRONMENT.getScreenDevices();
        }

        return LOCAL_DEVICES;
    }

    /**
     * Retrieves the primary screen device.
     * @return
     */
    public GraphicsDevice getPrimaryDevice() {
        if (PRIMARY_DEVICE == null) {
            PRIMARY_DEVICE = LOCAL_ENVIRONMENT.getDefaultScreenDevice();
        }

        return PRIMARY_DEVICE;
    }

    /**
     * Retrieves the usable available space of the primary GraphicsDevice.
     * @return A rectangle representing the primary device usable bounds.
     */
    public Rectangle getSysPrimaryUseableBounds() {
        if (PRIMARY_DEVICE_BOUNDS == null) {
            PRIMARY_DEVICE_BOUNDS = getUsableSpaceFor( PRIMARY_DEVICE );
        }

        return PRIMARY_DEVICE_BOUNDS;
    }

    /**
     * Calculates and returns the usable screen space for the provided GraphicsDevice.
     * @param device The GraphicsDevice we need the available usable space for.
     * @return A rectangle of the available usable space.
     */
    public Rectangle getUsableSpaceFor(final GraphicsDevice device) {
        final Rectangle deviceBounds = device.getDefaultConfiguration().getBounds();
        final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(device.getDefaultConfiguration());

        final Rectangle effectiveArea = new Rectangle();

        effectiveArea.x = deviceBounds.x + screenInsets.left;
        effectiveArea.y = deviceBounds.x + screenInsets.top;
        effectiveArea.height = deviceBounds.height - screenInsets.top - screenInsets.bottom;
        effectiveArea.width = deviceBounds.width - screenInsets.left - screenInsets.right;

        return effectiveArea;
    }
    /**
     * Returns the GraphicsDevice that contains the specified point.
     * @param p
     * @return
     */
    public GraphicsDevice getGraphicsDeviceAt(final Point p) {
        GraphicsDevice device = null;
        GraphicsDevice[] devices = LOCAL_ENVIRONMENT.getScreenDevices();

        for (GraphicsDevice gd : devices) {
            Rectangle bounds = gd.getDefaultConfiguration().getBounds();

            if (bounds.contains(p)) {
                device = gd;
                break;
            }
        }

        return device;
    }

    /**
     * Returns the cursors location on screen.
     * @return
     */
    public Point getCursorPos() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    /**
     * Calculates the position the list dock should be placed at.
     * @param width The width of the dock.
     * @return
     */
    public Point getPlacementForTopRight(1) {
        Point p = new Point();

        p.x = (int) PRIMARY_DEVICE_BOUNDS.getMaxX() - DOCK_OFFSETX;
        p.y = DOCK_OFFSETY;

        return p;
    }

    /**
     *
     * @param width The width of the dock.
     * @return
     */
    public Point getPlacementForTopLeft() {
        Point p = new Point();

        p.x = (int) PRIMARY_DEVICE_BOUNDS.getMinX() + (DOCK_OFFSETX / 4) ;
        p.y = DOCK_OFFSETY;

        return p;
    }


}
