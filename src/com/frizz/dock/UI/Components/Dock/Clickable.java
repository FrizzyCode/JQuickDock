package com.frizz.dock.UI.Components.Dock;

import com.alee.extended.menu.WebDynamicMenuItem;
import com.alee.laf.label.WebLabel;

import javax.swing.*;
import java.awt.*;

/**
 * @author steven
 * @version 1.0.0a
 */
public abstract class Clickable extends WebDynamicMenuItem {

    /**
     *
     *
     */
    Clickable() {

    }

    @Override
    public abstract JToolTip createToolTip();

    @Override
    public abstract void setName(String name);

    @Override
    public abstract void setText(String text);


    @Override
    public abstract String getName();

    @Override
    public abstract String getText();

    @Override
    public abstract String getToolTipText();

}
