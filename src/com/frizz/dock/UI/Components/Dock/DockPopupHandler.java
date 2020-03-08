package com.frizz.dock.UI.Components.Dock;

import com.alee.laf.menu.WebCheckBoxMenuItem;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebPopupMenu;
import com.frizz.dock.Display.DockHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class DockPopupHandler implements ActionListener {

    /**
     *
     */
    private final DockHandler handler;

    /**
     *
     */
    private WebPopupMenu dockPopupMenu;
    private WebPopupMenu shortCutPopupMenu;

    /**
     *
     */
    private WebMenu optionsMenu;

    /**
     *
     */
    private WebMenu positionMenu;

    /**
     *
     */
    private WebMenu moveToMenu;

    /**
     *
     */
    private WebMenuItem posLeftItem;

    /**
     *
     */
    private WebMenuItem posRightItem;

    /**
     *
     */
    private WebMenuItem posTopItem;

    /**
     *
     */
    private WebMenuItem posBottomItem;

    /**
     *
     */
    private WebCheckBoxMenuItem posCursorItem;

    /**
     *
     */
    private WebCheckBoxMenuItem hidingDockItem;

    /**
     *
     */
    private WebCheckBoxMenuItem magnifyItem;

    /**
     *
     */
    private WebMenuItem optionsItem;

    /**
     *
     */
    private WebMenuItem deleteItem;

    /**
     *
     */
    private WebMenuItem showExplorerItem;

    /**
     *
     */
    private WebMenuItem dockSettingsItem;

    /**
     *
     */
    private Component invoker;

    /**
     *
     */
    public DockPopupHandler(final DockHandler handler) {
        this.handler = handler;

        dockPopupMenu = new WebPopupMenu();
        shortCutPopupMenu = new WebPopupMenu();

        optionsMenu = new WebMenu("Options");
        positionMenu = new WebMenu("Position on screen");
        moveToMenu = new WebMenu("Move to display..");

        posCursorItem = new WebCheckBoxMenuItem("Display dock on cursor");
        hidingDockItem = new WebCheckBoxMenuItem("Turn on auto hiding");
        magnifyItem = new WebCheckBoxMenuItem("Turn on magnification");

        posBottomItem = new WebMenuItem("Bottom of screen");
        posTopItem = new WebMenuItem("Top of screen");
        posLeftItem = new WebMenuItem("Left side of screen");
        posRightItem = new WebMenuItem("Right side of screen");

        optionsItem = new WebMenuItem("Shortcut options");
        deleteItem = new WebMenuItem("Delete shortcut");
        showExplorerItem = new WebMenuItem("Show shortcut in - "); //TODO platform independent verbiage

        dockSettingsItem = new WebMenuItem("Dock settings");

        //TODO move to menu population

        positionMenu.add(posTopItem);
        positionMenu.add(posRightItem);
        positionMenu.add(posBottomItem);
        positionMenu.add(posLeftItem);
        positionMenu.addSeparator();
        positionMenu.add(posCursorItem);


        dockPopupMenu.add(positionMenu);
        dockPopupMenu.add(moveToMenu);
        dockPopupMenu.addSeparator();
        dockPopupMenu.add(hidingDockItem);
        dockPopupMenu.add(magnifyItem);
        dockPopupMenu.addSeparator();
        dockPopupMenu.add(dockSettingsItem);

        optionsMenu.add(optionsItem);
        optionsMenu.add(deleteItem);

        shortCutPopupMenu.add(optionsMenu);
        shortCutPopupMenu.add(showExplorerItem);

        posTopItem.addActionListener(this);
        posRightItem.addActionListener(this);
        posBottomItem.addActionListener(this);
        posLeftItem.addActionListener(this);
        posCursorItem.addActionListener(this);

        hidingDockItem.addActionListener(this);
        magnifyItem.addActionListener(this);
        dockSettingsItem.addActionListener(this);

        optionsItem.addActionListener(this);
        deleteItem.addActionListener(this);
        showExplorerItem.addActionListener(this);

        posTopItem.setName("pos-top");
        posRightItem.setName("pos-right");
        posBottomItem.setName("pos-bottom");
        posLeftItem.setName("pos-left");
        posCursorItem.setName("pos-cursor");

        hidingDockItem.setName("hide-dock");
        magnifyItem.setName("magnify-items");
        dockSettingsItem.setName("dock-settings");

        optionsItem.setName("shortcut-options");
        deleteItem.setName("shortcut-delete");
        showExplorerItem.setName("shortcut-explorer");
    }

    /**
     * When menu items are clicked, the actions are processed here.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Used to determine what action to perform.
        String name = null;

        //Get the source of the ActionEvent.
        if (e.getSource() instanceof WebCheckBoxMenuItem) {
            WebCheckBoxMenuItem item = (WebCheckBoxMenuItem) e.getSource();
            name = item.getName();
        } else if (e.getSource() instanceof WebMenuItem) {
            WebMenuItem item = (WebMenuItem) e.getSource();
            name = item.getName();
        }

        //Verify Sting name is not null and not empty.
        if (name != null) {
            if (!name.isEmpty()) {
                //Determine what action to perform based off the name of the source
                switch (name) {
                    case "pos-top":
                        repositionAction("pos-top");
                            break;
                    case "pos-right":
                        repositionAction("pos-right");
                        break;
                    case "pos-bottom":
                        repositionAction("pos-bottom");
                        break;
                    case "pos-left":
                        repositionAction("pos-left");
                        break;
                    case "pos-cursor":
                        repositionAction("pos-cursor");
                        break;
                    case "hide-dock":
                        dockAction("hide-dock");
                        break;
                    case "magnify-items":
                        dockAction("magnify-items");
                        break;
                    case "dock-settings":
                        dockAction("dock-settings");
                        break;
                    case "shortcut-options":
                        shortcutAction("shortcut-options");
                        break;
                    case "shortcut-delete":
                        shortcutAction("shortcut-delete");
                        break;
                    case "shortcut-explorer":
                        shortcutAction("shortcut-explorer");
                        break;
                }
            }
        }
    }



    /**
     * Returns true or false depending on the visibility state
     * of the dock shortcut popup menu.
     * @return
     */
    public boolean isShortcutPopupShowing() {
        return shortCutPopupMenu.isShowing();
    }

    /**
     * Returns true or false depending on the visibility state
     * of the dock popup menu.
     * @return boolean
     */
    public boolean isDockPopupShowing() {
        return dockPopupMenu.isShowing();
    }

    /**
     * Shows either the shortcut popup menu or separator popup menu,
     * depending on the invoker.
     * @param invoker Can either be a DockShortcut or DockSeparator
     * @param p The point of the mouse click event.
     */
    public void show(Component invoker, Point p) {
        this.invoker = invoker;

        //Determine the instance of the invoker.
        if (invoker instanceof DockShortcut) {
            DockShortcut shortcut = (DockShortcut) invoker;
            //Enable/Disable items in the popup depending on variables
            processMenuComponents(shortcut);

            //Only show popup if its not showing
            if (!shortCutPopupMenu.isShowing()) {
                shortCutPopupMenu.show(invoker, p.x, p.y);
            }
        } else if (invoker instanceof DockSeparator) {
            DockSeparator separator = (DockSeparator) invoker;
            processMenuComponents(separator);

            if (!dockPopupMenu.isShowing()) {
                dockPopupMenu.show(invoker, p.x, p.y);
            }
        }

    }

    /**
     * Hides the popup menus.
     */
    public void hide() {
        if (shortCutPopupMenu.isShowing()) {
            shortCutPopupMenu.setVisible(false);
        }

        if (dockPopupMenu.isShowing()) {
            dockPopupMenu.setVisible(false);
        }

    }

    /**
     *
     * @param command
     */
    private void repositionAction(final String command) {

    }

    /**
     *
     * @param command
     */
    private void dockAction(final String command) {

    }

    /**
     *
     * @param command
     */
    private void shortcutAction(final String command) {
        DockShortcut shortcut = (DockShortcut) invoker;

        if (command.equals("shortcut-delete")) {
            handler.deleteShortcut(shortcut);
        } else if (command.equals("shortcut-explorer")) {
            try {
                Desktop.getDesktop().open(new File(shortcut.getSourcePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Enables/Disables menu items depending on the DockShortcut internal settings
     * @param icon
     */
    private void processMenuComponents(final DockShortcut icon) {
        if (!icon.isDeleteAvail())
            deleteItem.setEnabled(false);
    }

    /**
     * Enables/Disables menu items on the dock popup menu.
     * @param icon
     */
    private void processMenuComponents(final DockSeparator icon) {

    }
}
