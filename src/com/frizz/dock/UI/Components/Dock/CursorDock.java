package com.frizz.dock.UI.Components.Dock;

import com.alee.extended.menu.DynamicMenuLayout;
import com.alee.extended.menu.DynamicMenuType;
import com.alee.extended.menu.WebDynamicMenu;
import com.alee.extended.menu.WebDynamicMenuItem;
import com.alee.managers.style.StyleId;

import com.alee.utils.SwingUtils;
import com.frizz.dock.Display.ScreenSpace;
import java.awt.*;
import java.util.prefs.Preferences;

/**
 *
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class CursorDock extends WebDynamicMenu {

    /**
     *
     */
    private final Preferences prefs = Preferences.userNodeForPackage( CursorDock.class );

    /**
     *
     */
     private int maxShortcuts = 20;

    /**
     *
     */
    private DynamicMenuType showType;

    /**
     *
     */
    private DynamicMenuType hideType;


    /**
     *
     */
    private boolean inUse = true;

    /**
     * Constructs a CursorDock.
     */
    public CursorDock() {
        int style = prefs.getInt( "dock-enter-anim", 1 );

        //TODO Set max shortcuts dynamically based off of screen space.
        if (style == 0) {
            maxShortcuts = 20;
            showType = DynamicMenuType.roll;
            hideType = DynamicMenuType.star;
        } else if (style == 1) {
            maxShortcuts = 15;
            showType = DynamicMenuType.list;
            hideType = DynamicMenuType.list;
        }

        setStyleId(StyleId.panelTransparent);
        setLayout(new DynamicMenuLayout());

        // Popup settings
        setAnimate ( true );
        setFadeStepSize ( 0.04f );
        setWindowOpaque ( false );
        setWindowOpacity ( 0f );
        setFollowInvoker ( true );

        // Menu settings
        setType( showType );
        setHideType( hideType );
        setRadius ( 325 );
        setStartingAngle ( 0 );
        setAngleRange ( 360 );
        setAlwaysOnTop(true);
    }

    /**
     * We override the addItem function of WebDynamicMenu due to the original implementation
     * adding mouse listeners on the buttons. We will handle the mouse listeners with our own
     * implementation.
     *
     * @param menuItem
     * @return
     */
    @Override
    public WebDynamicMenuItem addItem(final WebDynamicMenuItem menuItem) {
        super.items.add(menuItem);

        menuItem.setEnabled (menuItem.getAction () != null);
        menuItem.setMargin (menuItem.getMargin ());

        add(menuItem);

        return menuItem;
    }

    /**
     *
     * @param sourcePath
     * @return
     */
    public WebDynamicMenuItem createItem(final String sourcePath) {
        WebDynamicMenuItem item = new WebDynamicMenuItem(  );

        return item;
    }

    /**
     *
     * @param type
     */
    public void setShowAmimation(DynamicMenuType type) {
        showType = type;
        setType( type );
    }

    /**
     *
     * @param type
     */
    public void setHideAnimation(DynamicMenuType type) {
        hideType = type;
        setHideType( type );
    }

    /**
     *
     * @return
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     *
     * @return
     */
    public final int getMaxShortcuts() {
        return maxShortcuts;
    }

    /**
     *
     * @return
     */
    public DynamicMenuType getShowType() {
        return showType;
    }
}
