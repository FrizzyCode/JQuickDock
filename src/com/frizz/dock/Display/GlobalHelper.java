package com.frizz.dock.Display;

import com.alee.extended.menu.DynamicMenuType;
import com.frizz.dock.UI.Components.Dock.CursorDock;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class GlobalHelper extends NativeKeyAdapter {

    /**
     *
     */
    final Preferences prefs = Preferences.userNodeForPackage( GlobalHelper.class );

    /**
     *
     */
    final Logger logger = Logger.getLogger( GlobalScreen.class.getPackage().getName() );

    /**
     *
     */
    private final int esc = NativeKeyEvent.VC_ESCAPE;

    /**
     *
     */
    private int mask1, mask2, button1 = -1;

    /**
     *
     */
    private List<Integer> pressed = new ArrayList<>(  );

    /**
     *
     */
    private CursorDock dock;

    /**
     *
     */
    GlobalHelper(CursorDock dock) {
        mask1 = prefs.getInt( "mask1", NativeKeyEvent.VC_CONTROL);
        mask2 = prefs.getInt( "mask2", NativeKeyEvent.VC_SHIFT );
        button1 = prefs.getInt( "button1", NativeKeyEvent.VC_V );
        this.dock = dock;

        logger.setLevel( Level.WARNING );
        logger.setUseParentHandlers( false );
    }

    @Override public void nativeKeyPressed ( NativeKeyEvent ne ) {
        int keycode = ne.getKeyCode();
        //System.out.println("PRESSED KEYCODE ====  " +keycode);

        if (keycode != esc) {

            if (!pressed.contains( keycode )) {

                if (keycode == mask1 || keycode == mask2 || keycode == button1) {
                    System.out.println( "KEYCODE MATCHES REQUIREMENTS" );
                    pressed.add( keycode );
                }

            }

            if (pressed.size() == 3 ) {
                System.out.println("LIST SIZE MATCHES REQUIREMENTS.");

                boolean mask1pressed = false, mask2pressed = false, button1pressed = false;

                for (Integer i : pressed) {
                    if (i == mask1) {
                        mask1pressed = true;
                    } else if (i == mask2) {
                        mask2pressed = true;
                    } else if (i == button1) {
                        button1pressed = true;
                    }
                }

                if (mask1pressed) {
                    if (mask2pressed) {
                        if (button1pressed) {
                            if (!dock.isShowing()) {
                                showDock();
                            } else {
                                dock.hideMenu();
                            }
                        }
                    }
                }

            }


        } else {
            if (dock.isShowing())
                dock.hideMenu();
        }

    }

    @Override public void nativeKeyReleased ( NativeKeyEvent ne ) {
        int keycode = ne.getKeyCode();

        if (pressed.contains( keycode )) {
            pressed.remove(Integer.valueOf( keycode ));
        }
    }

    /**
     *
     */
    protected void showDock() {

        ScreenSpace ss = ScreenSpace.getInstance();

        if (dock.getShowType() == DynamicMenuType.roll) {
            dock.showMenu( null, ss.getCursorPos());

        } else if (dock.getShowType() == DynamicMenuType.list) {
            Point p;

            if (prefs.getInt( "list-placement", 1 ) == 1) {
                p = ss.getPlacementForTopRight(  );
            } else {
                p = ss.getPlacementForTopLeft();
            }


            dock.showMenu( null, p);
        }
    }
}
