package com.frizz.dock.UI.Listeners;

import com.frizz.dock.UI.Components.Dock.Clickable;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 *
 */
public class IconClickEvent {

    /**
     *
     */
    private final Clickable source;


    /**
     *
     * @param source
     * @param mouseButton
     */
    public IconClickEvent(Clickable source) {
        this.source = source;
    }

    /**
     *
     * @return
     */
    public Clickable getEventSource() {
        return source;
    }

}
