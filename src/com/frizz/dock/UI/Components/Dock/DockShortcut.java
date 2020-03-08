package com.frizz.dock.UI.Components.Dock;

import com.alee.extended.list.ThumbnailGenerator;
import com.alee.extended.window.PopOverDirection;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.tooltip.WebToolTip;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.FileUtils;
import com.frizz.dock.UI.Drag.ComponentMover;
import com.frizz.dock.Utilities.SafeIcon;

import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 * @see Clickable
 * @see Comparable
 * @see Serializable
 */
public final class DockShortcut extends Clickable implements Comparable, Serializable {

    /**
     * Determines whether this shortcut can be deleted/removed from the dock.
     */
    private boolean isDeleteAvail;

    /**
     * The name of the DockShortcut.
     */
    private String name;

    /**
     * The path of the shortcut to a file or web page.
     */
    private String sourcePath;

    /**
     * The image used for the DockShortcut.
     * When the DockShortcut is resized, the image should be resized as well.
     * If the DockShortcut represents a file or application, the system icon
     * is pulled and wrapped into class SafeIcon to make resizing of Icons
     * easier.
     *
     * @see com.frizz.dock.Utilities.SafeIcon
     */
    private Icon icon;

    /**
     * The size of the icon.
     * The size of the icon should be the same as other icons contained within the dock.
     * When preferences are altered in the PreferenceBundle during runtime, a PreferenceChangeEvent
     * is fired. This applies to icon sizes, and should be handled in realtime.
     */
    private Dimension size;

    /**
     * The JLabel of the tooltip.
     */
    private JLabel toolTipLabel;

    /**
     *
     */
    private DockIconListeners listeners;


    /**
     * Constructs a new DockShortcut. Since the size is not specified, it uses the default size.
     *
     * @param sourcePath
     */
    public DockShortcut(String sourcePath) {
        new DockShortcut(sourcePath, sourcePath);
    }

    /**
     *
     * @param sourcePath
     * @param name
     */
    public DockShortcut(String sourcePath, String name) {
        new DockShortcut(sourcePath, name, new Dimension(32, 32));
    }

    /**
     * Constructs a new DockShortcut with the specified name and size.
     *
     * @param sourcePath
     * @param size The size of the DockShortcut.
     */
    public DockShortcut(String sourcePath, String name, Dimension size) {
        new DockShortcut(sourcePath, name, size, null);
    }

    /**
     * @param
     * @return
     */
    public DockShortcut(String sourcePath, String name, Dimension size, ImageIcon icon) {
        this.sourcePath = sourcePath;
        this.name = name;
        this.size = size;
        this.icon = icon;
        toolTipLabel = new JLabel( name );

        toolTipLabel.setForeground( Color.WHITE );

        setIcon(icon);
        setSize(size);
        setMargin( new Insets(10, 10, 10, 10) );

        TooltipManager.addTooltip( this, toolTipLabel, TooltipWay.up, 0 );
    }

    @Override
    public JToolTip createToolTip() {
        WebToolTip tip = new WebToolTip(  );
        return tip;
    }

    /**
     * If the shortcut can be deleted.
     * @return boolean
     */
    public boolean isDeleteAvail() {
        return isDeleteAvail;
    }


    /**
     * Sets if the shortcut is deletable.
     * @param deleteAvail
     */
    public void setDeleteAvail(boolean deleteAvail) {
        isDeleteAvail = deleteAvail;
    }

    /**
     * The file path or web page URL the shortcut points to.
     * @return
     */
    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * Sets the size of the shortcut.
     * @param dim
     */
    @Override
    public void setSize(Dimension dim) {
        //TODO resize icon
    }

    /**
     * Sets the name of the shortcut
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param text
     */
    @Override
    public void setText(String text) {

    }


    /**
     * Retrieves the name of the shortcut.
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    @Override
    public String getText() {
        return toolTipLabel.getText();
    }

    /**
     * @return
     */
    @Override
    public ImageIcon getIcon() {
        return (ImageIcon) icon;
    }

    /**
     * @return
     */
    @Override
    public String getToolTipText() {
        return toolTipLabel.getText();
    }

    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        DockShortcut shortcut = (DockShortcut) o;

        if (shortcut.getName().equals(name) && shortcut.getSourcePath().equals(sourcePath)) {
            return 0;
        }
        return -1;
    }
}